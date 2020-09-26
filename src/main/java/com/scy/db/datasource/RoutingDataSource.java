package com.scy.db.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.scy.core.CollectionUtil;
import com.scy.core.format.MessageUtil;
import com.scy.core.thread.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;

/**
 * RoutingDataSource
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/26.
 */
@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    public static final String DATA_SOURCE_LOOKUP_KEY = "data_source_lookup_key";

    public static final String MASTER = "master";

    public static final String SLAVE = "slave";

    public static void routingMaster() {
        ThreadLocalUtil.put(DATA_SOURCE_LOOKUP_KEY, MASTER);
    }

    public static void routingSlave() {
        ThreadLocalUtil.put(DATA_SOURCE_LOOKUP_KEY, SLAVE);
    }

    public static String getRouting() {
        return (String) ThreadLocalUtil.get(DATA_SOURCE_LOOKUP_KEY);
    }

    @NonNull
    @Override
    protected DataSource determineTargetDataSource() {
        DataSource dataSource = super.determineTargetDataSource();
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        log.info(MessageUtil.format("determineTargetDataSource", "url", druidDataSource.getUrl()));
        return dataSource;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getRouting();
    }

    public static RoutingDataSource getRoutingDataSource(DruidDataSource master, DruidDataSource slave) {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(CollectionUtil.newHashMap(MASTER, master, SLAVE, slave));
        routingDataSource.setLenientFallback(Boolean.FALSE);
        return routingDataSource;
    }
}
