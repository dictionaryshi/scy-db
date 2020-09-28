package com.scy.db.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.scy.core.spring.ApplicationContextUtil;
import com.scy.db.model.bo.DruidMonitorBO;
import com.scy.db.properties.DruidDataSourceProperties;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DruidUtil
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/25.
 */
public class DruidUtil {

    private DruidUtil() {
    }

    public static DruidDataSource getDruidDataSource(DruidDataSourceProperties druidDataSourceProperties) {
        DruidDataSource druidDataSource = new DruidDataSource();

        // 数据库连接地址
        druidDataSource.setUrl(druidDataSourceProperties.getUrl());

        // 数据库用户名
        druidDataSource.setUsername(druidDataSourceProperties.getUsername());

        // 数据库密码
        druidDataSource.setPassword(druidDataSourceProperties.getPassword());

        // 最小连接池数量
        druidDataSource.setMinIdle(druidDataSourceProperties.getMinIdle());

        // 初始化时建立物理连接的个数
        druidDataSource.setInitialSize(druidDataSourceProperties.getInitialSize());

        // 最大连接池数量
        druidDataSource.setMaxActive(druidDataSourceProperties.getMaxActive());

        // 初始化连接池时会填充到minIdle数量。连接池中的minIdle数量以内的连接, 空闲时间超过minEvictableIdleTimeMillis, 则会执行keepAlive操作。
        druidDataSource.setKeepAlive(Boolean.TRUE);

        // 使用公平锁
        druidDataSource.setUseUnfairLock(Boolean.FALSE);

        // 同步初始化
        druidDataSource.setAsyncInit(Boolean.FALSE);

        // 大事务时间阈值
        druidDataSource.setTransactionThresholdMillis(10_000);

        // 获取连接时最大等待时间
        druidDataSource.setMaxWait(10_000);

        // 连接保持空闲而不被驱逐的最小时间
        druidDataSource.setMinEvictableIdleTimeMillis(300_000L);

        // Destroy线程会检测连接的间隔时间, 如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。testWhileIdle的判断依据。
        druidDataSource.setTimeBetweenEvictionRunsMillis(60_000L);

        // 用来检测连接是否有效的sql
        druidDataSource.setValidationQuery("select 'x'");

        // 检测连接是否有效的超时时间, 单位:秒。
        druidDataSource.setValidationQueryTimeout(5);

        // 建议配置为true, 不影响性能, 并且保证安全性。申请连接的时候检测, 如果空闲时间大于timeBetweenEvictionRunsMillis, 执行validationQuery检测连接是否有效。
        druidDataSource.setTestWhileIdle(Boolean.TRUE);

        // 申请连接时执行validationQuery检测连接是否有效, 做了这个配置会降低性能。
        druidDataSource.setTestOnBorrow(Boolean.FALSE);

        // 归还连接时执行validationQuery检测连接是否有效, 做了这个配置会降低性能。
        druidDataSource.setTestOnReturn(Boolean.FALSE);

        return druidDataSource;
    }

    public static List<DruidMonitorBO> getDruidMonitorInfo() {
        Map<String, DruidDataSource> druidDataSourceMap = ApplicationContextUtil.getBeansOfType(DruidDataSource.class);
        return druidDataSourceMap.values().stream().map(druidDataSource -> {
            DruidMonitorBO druidMonitorBO = new DruidMonitorBO();
            druidMonitorBO.setUrl(druidDataSource.getUrl());
            druidMonitorBO.setMinIdle(druidDataSource.getMinIdle());
            druidMonitorBO.setMaxActive(druidDataSource.getMaxActive());
            druidMonitorBO.setAllCount(druidDataSource.getPoolingCount() + druidDataSource.getActiveCount());
            druidMonitorBO.setPoolingCount(druidDataSource.getPoolingCount());
            druidMonitorBO.setActiveCount(druidDataSource.getActiveCount());
            return druidMonitorBO;
        }).collect(Collectors.toList());
    }
}
