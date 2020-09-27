package com.scy.db.config;

import com.scy.core.CollectionUtil;
import com.scy.core.ObjectUtil;
import com.scy.core.StringUtil;
import com.scy.core.enums.ResponseCodeEnum;
import com.scy.core.exception.BusinessException;
import com.scy.core.spring.ApplicationContextUtil;
import com.scy.db.properties.DbProperties;
import com.scy.db.properties.DruidDataSourceProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
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

        dbPropertiesList.forEach(this::registry);
    }

    private void registry(DbProperties dbProperties) {
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

        if (StringUtil.isEmpty(druidDataSourceProperties.getPassword())) {
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
