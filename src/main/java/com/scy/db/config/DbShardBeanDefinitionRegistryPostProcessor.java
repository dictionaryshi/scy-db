package com.scy.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.scy.db.mybatis.SqlSessionFactoryBean;
import com.scy.db.properties.DbProperties;
import com.scy.db.transaction.ForceMasterDataSourceTransactionManager;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author : shichunyang
 * Date    : 2023/5/5
 * Time    : 4:52 下午
 * ---------------------------------------
 * Desc    : DbShardBeanDefinitionRegistryPostProcessor
 */
public class DbShardBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry) throws BeansException {
        try {
            registry(registry);
        } catch (Exception e) {
            throw new BeanCreationException("DbShardBeanDefinitionRegistryPostProcessor error", e);
        }
    }

    private void registry(BeanDefinitionRegistry registry) throws Exception {
        Map<String, DataSource> dataSourceMap = Sharding.createDataSourceMap();
        registryDruid(registry, dataSourceMap);

        DataSource dataSource = Sharding.getDataSource(dataSourceMap);
        registryDataSource(registry, dataSource);

        registryTransactionManager(registry);

        registrySqlSessionFactory(registry);

        registryMapperScannerConfigurer(registry);
    }

    private void registryMapperScannerConfigurer(BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
        beanDefinitionBuilder.addPropertyValue("processPropertyPlaceHolders", Boolean.TRUE);
        beanDefinitionBuilder.addPropertyValue("sqlSessionFactoryBeanName", "wxjjSqlSessionFactory");
        beanDefinitionBuilder.addPropertyValue("basePackage", "com.wx.dao.warehouse.mapper");
        registry.registerBeanDefinition("wxjjMapperScannerConfigurer", beanDefinitionBuilder.getBeanDefinition());
    }

    private void registrySqlSessionFactory(BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionFactoryBean.class);
        beanDefinitionBuilder.addPropertyReference("dataSource", "wxjjDataSource");

        DbProperties dbProperties = new DbProperties();
        dbProperties.setUseGeneratedKeys(true);
        dbProperties.setDefaultStatementTimeout(10);
        beanDefinitionBuilder.addPropertyValue("dbProperties", dbProperties);
        registry.registerBeanDefinition("wxjjSqlSessionFactory", beanDefinitionBuilder.getBeanDefinition());
    }

    private void registryTransactionManager(BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ForceMasterDataSourceTransactionManager.class);
        beanDefinitionBuilder.addConstructorArgReference("wxjjDataSource");
        registry.registerBeanDefinition("wxjjTransactionManager", beanDefinitionBuilder.getBeanDefinition());
    }

    private void registryDataSource(BeanDefinitionRegistry registry, DataSource dataSource) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DataSource.class, () -> dataSource);
        registry.registerBeanDefinition("wxjjDataSource", beanDefinitionBuilder.getBeanDefinition());
    }

    private void registryDruid(BeanDefinitionRegistry registry, Map<String, DataSource> dataSourceMap) {
        dataSourceMap.forEach((databaseName, value) -> {
            BeanDefinitionBuilder masterBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DruidDataSource.class, () -> (DruidDataSource) value);
            masterBeanDefinitionBuilder.setInitMethodName("init");
            masterBeanDefinitionBuilder.setDestroyMethodName("close");
            registry.registerBeanDefinition(databaseName, masterBeanDefinitionBuilder.getBeanDefinition());
        });
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }
}
