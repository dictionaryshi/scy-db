package com.scy.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.scy.core.CollectionUtil;
import com.scy.core.ObjectUtil;
import com.scy.core.StringUtil;
import com.scy.core.enums.ResponseCodeEnum;
import com.scy.core.exception.BusinessException;
import com.scy.core.spring.ApplicationContextUtil;
import com.scy.db.constant.DbConstant;
import com.scy.db.datasource.RoutingDataSource;
import com.scy.db.model.ao.DbRegistryAO;
import com.scy.db.mybatis.SqlSessionFactoryBean;
import com.scy.db.properties.DbProperties;
import com.scy.db.properties.DruidDataSourceProperties;
import com.scy.db.transaction.ForceMasterDataSourceTransactionManager;
import com.scy.db.util.DruidUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.lang.NonNull;

import java.util.Collections;
import java.util.List;

/**
 * DbBeanDefinitionRegistryPostProcessor
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/27.
 */
public class DbBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry) throws BeansException {
        List<DbProperties> dbPropertiesList = Binder.get(ApplicationContextUtil.getApplicationContext().getEnvironment()).bind(DbProperties.PREFIX, Bindable.listOf(DbProperties.class)).orElse(Collections.emptyList());
        if (CollectionUtil.isEmpty(dbPropertiesList)) {
            throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "缺少 db config");
        }

        checkProperties(dbPropertiesList);

        dbPropertiesList.forEach(dbProperties -> registry(dbProperties, registry));
    }

    private void registry(DbProperties dbProperties, BeanDefinitionRegistry registry) {
        DbRegistryAO dbRegistryAO = new DbRegistryAO();
        dbRegistryAO.setDbProperties(dbProperties);
        dbRegistryAO.setRegistry(registry);
        dbRegistryAO.setMasterBeanName(dbProperties.getName() + DbConstant.DATA_SOURCE_MASTER);
        dbRegistryAO.setSlaveBeanName(dbProperties.getName() + DbConstant.DATA_SOURCE_SLAVE);
        dbRegistryAO.setDataSourceBeanName(dbProperties.getName() + DbConstant.DATA_SOURCE);
        dbRegistryAO.setTransactionManagerBeanName(dbProperties.getName() + DbConstant.TRANSACTION_MANAGER);
        dbRegistryAO.setSqlSessionFactoryBeanName(dbProperties.getName() + DbConstant.SQL_SESSION_FACTORY);
        dbRegistryAO.setSqlSessionTemplateBeanName(dbProperties.getName() + DbConstant.SQL_SESSION_TEMPLATE);
        dbRegistryAO.setMapperScannerConfigurerBeanName(dbProperties.getName() + DbConstant.MAPPER_SCANNER_CONFIGURER);

        registryDruid(dbRegistryAO);

        registryDataSource(dbRegistryAO);

        registryTransactionManager(dbRegistryAO);

        registrySqlSessionFactory(dbRegistryAO);

        registrySqlSessionTemplate(dbRegistryAO);

        registryMapperScannerConfigurer(dbRegistryAO);
    }

    private void registryMapperScannerConfigurer(DbRegistryAO dbRegistryAO) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
        beanDefinitionBuilder.addPropertyValue("processPropertyPlaceHolders", Boolean.TRUE);
        beanDefinitionBuilder.addPropertyValue("sqlSessionFactoryBeanName", dbRegistryAO.getSqlSessionFactoryBeanName());
        beanDefinitionBuilder.addPropertyValue("basePackage", dbRegistryAO.getDbProperties().getBasePackages());
        dbRegistryAO.getRegistry().registerBeanDefinition(dbRegistryAO.getMapperScannerConfigurerBeanName(), beanDefinitionBuilder.getBeanDefinition());
    }

    private void registrySqlSessionTemplate(DbRegistryAO dbRegistryAO) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionTemplate.class);
        beanDefinitionBuilder.addConstructorArgReference(dbRegistryAO.getSqlSessionFactoryBeanName());
        dbRegistryAO.getRegistry().registerBeanDefinition(dbRegistryAO.getSqlSessionTemplateBeanName(), beanDefinitionBuilder.getBeanDefinition());
    }

    private void registrySqlSessionFactory(DbRegistryAO dbRegistryAO) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionFactoryBean.class);
        beanDefinitionBuilder.addPropertyReference("dataSource", dbRegistryAO.getDataSourceBeanName());
        beanDefinitionBuilder.addPropertyValue("dbProperties", dbRegistryAO.getDbProperties());
        dbRegistryAO.getRegistry().registerBeanDefinition(dbRegistryAO.getSqlSessionFactoryBeanName(), beanDefinitionBuilder.getBeanDefinition());
    }

    private void registryTransactionManager(DbRegistryAO dbRegistryAO) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ForceMasterDataSourceTransactionManager.class);
        beanDefinitionBuilder.addConstructorArgReference(dbRegistryAO.getDataSourceBeanName());
        dbRegistryAO.getRegistry().registerBeanDefinition(dbRegistryAO.getTransactionManagerBeanName(), beanDefinitionBuilder.getBeanDefinition());
    }

    private void registryDataSource(DbRegistryAO dbRegistryAO) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RoutingDataSource.class);
        beanDefinitionBuilder.addPropertyReference("masterDruidDataSource", dbRegistryAO.getMasterBeanName());
        beanDefinitionBuilder.addPropertyReference("slaveDruidDataSource", dbRegistryAO.getSlaveBeanName());
        dbRegistryAO.getRegistry().registerBeanDefinition(dbRegistryAO.getDataSourceBeanName(), beanDefinitionBuilder.getBeanDefinition());
    }

    private void registryDruid(DbRegistryAO dbRegistryAO) {
        BeanDefinitionBuilder masterBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DruidDataSource.class, () -> DruidUtil.getDruidDataSource(dbRegistryAO.getDbProperties().getMaster()));
        masterBeanDefinitionBuilder.setInitMethodName("init");
        masterBeanDefinitionBuilder.setDestroyMethodName("close");
        dbRegistryAO.getRegistry().registerBeanDefinition(dbRegistryAO.getMasterBeanName(), masterBeanDefinitionBuilder.getBeanDefinition());

        BeanDefinitionBuilder slaveBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DruidDataSource.class, () -> DruidUtil.getDruidDataSource(dbRegistryAO.getDbProperties().getSlave()));
        slaveBeanDefinitionBuilder.setInitMethodName("init");
        slaveBeanDefinitionBuilder.setDestroyMethodName("close");
        dbRegistryAO.getRegistry().registerBeanDefinition(dbRegistryAO.getSlaveBeanName(), slaveBeanDefinitionBuilder.getBeanDefinition());
    }

    private void checkProperties(List<DbProperties> dbPropertiesList) {
        dbPropertiesList.forEach(dbProperties -> {
            if (StringUtil.isEmpty(dbProperties.getName())) {
                throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "缺少 name");
            }

            if (StringUtil.isEmpty(dbProperties.getBasePackages())) {
                throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "缺少 basePackages");
            }

            if (ObjectUtil.isNull(dbProperties.getMaster())) {
                throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "缺少 master");
            }

            if (ObjectUtil.isNull(dbProperties.getSlave())) {
                throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "缺少 slave");
            }

            checkDruidDataSourceProperties(dbProperties.getMaster());
            checkDruidDataSourceProperties(dbProperties.getSlave());
        });
    }

    private void checkDruidDataSourceProperties(DruidDataSourceProperties druidDataSourceProperties) {
        if (StringUtil.isEmpty(druidDataSourceProperties.getUrl())) {
            throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "缺少 url");
        }

        if (StringUtil.isEmpty(druidDataSourceProperties.getUsername())) {
            throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "缺少 username");
        }

        if (ObjectUtil.isNull(druidDataSourceProperties.getPassword())) {
            throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "缺少 password");
        }

        if (ObjectUtil.isNull(druidDataSourceProperties.getMinIdle())) {
            throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "缺少 minIdle");
        }

        if (ObjectUtil.isNull(druidDataSourceProperties.getInitialSize())) {
            throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "缺少 initialSize");
        }

        if (ObjectUtil.isNull(druidDataSourceProperties.getMaxActive())) {
            throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "缺少 maxActive");
        }
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }
}
