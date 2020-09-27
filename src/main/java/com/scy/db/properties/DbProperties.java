package com.scy.db.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DbProperties
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/27.
 */
@Getter
@Setter
@ToString
public class DbProperties {

    public static final String PREFIX = "dbConfig";

    private String name;

    private String basePackages;

    private Boolean useGeneratedKeys;

    private Integer defaultStatementTimeout;

    private DruidDataSourceProperties master;

    private DruidDataSourceProperties slave;
}
