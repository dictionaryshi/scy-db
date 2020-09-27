package com.scy.db.model.ao;

import com.scy.db.properties.DbProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * DbRegistryAO
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/27.
 */
@Getter
@Setter
@ToString
public class DbRegistryAO {

    private DbProperties dbProperties;

    private BeanDefinitionRegistry registry;

    private String masterBeanName;

    private String slaveBeanName;

    private String dataSourceBeanName;

    private String sqlSessionFactoryBeanName;

    private String sqlSessionTemplateBeanName;

    private String transactionManagerBeanName;
}
