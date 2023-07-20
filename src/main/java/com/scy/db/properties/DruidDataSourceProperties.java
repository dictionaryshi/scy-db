package com.scy.db.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DruidDataSourceProperties
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/25.
 */
@Getter
@Setter
@ToString
public class DruidDataSourceProperties {

    /**
     * 数据库连接地址(host + port + 数据库)
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 最小连接池数量
     */
    private Integer minIdle;

    /**
     * 初始化时建立物理连接的个数
     */
    private Integer initialSize;

    /**
     * 最大连接池数量
     */
    private Integer maxActive;

    public void setUrl(String url) {
        this.url = ("jdbc:mysql://" + url
                + "?characterEncoding=UTF8"
                + "&useSSL=false"
                + "&allowMultiQueries=true"
                + "&useAffectedRows=true"
                + "&zeroDateTimeBehavior=convertToNull"
                + "&useOldAliasMetadataBehavior=true"
                + "&failOverReadOnly=false"
                + "&serverTimezone=Asia/Shanghai"
                + "&socketTimeout=60000"
        );
    }
}
