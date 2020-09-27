package com.scy.db.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * DbConfig
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/27.
 */
@ConditionalOnProperty(value = "dbConfig.enabled", havingValue = "true", matchIfMissing = true)
public class DbConfig {

    @Bean
    public DbBeanDefinitionRegistryPostProcessor dbBeanDefinitionRegistryPostProcessor() {
        return new DbBeanDefinitionRegistryPostProcessor();
    }
}
