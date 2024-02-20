package com.scy.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.scy.core.CollectionUtil;
import com.scy.db.properties.DruidDataSourceProperties;
import com.scy.db.util.DruidUtil;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.mode.repository.standalone.StandalonePersistRepositoryConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.ReadwriteSplittingRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.rule.ReadwriteSplittingDataSourceRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.transaction.TransactionalReadQueryStrategy;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.audit.ShardingAuditStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.ComplexShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author : shichunyang
 * Date    : 2023/5/5
 * Time    : 1:01 下午
 * ---------------------------------------
 * Desc    : Sharding
 */
public class Sharding {

    private static ModeConfiguration createModeConfiguration() {
        return new ModeConfiguration("Standalone", new StandalonePersistRepositoryConfiguration("JDBC", new Properties()));
    }

    public static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>(16);

        DruidDataSourceProperties ds1 = new DruidDataSourceProperties();
        ds1.setUrl("127.0.0.1:3306/ds_1");
        ds1.setUsername("root");
        ds1.setPassword("");
        ds1.setMinIdle(10);
        ds1.setInitialSize(10);
        ds1.setMaxActive(20);
        DruidDataSource dataSource1 = DruidUtil.getDruidDataSource(ds1);
        dataSourceMap.put("ds_1", dataSource1);

        DruidDataSourceProperties ds1Read1 = new DruidDataSourceProperties();
        ds1Read1.setUrl("127.0.0.1:3306/ds_1_read1");
        ds1Read1.setUsername("root");
        ds1Read1.setPassword("");
        ds1Read1.setMinIdle(10);
        ds1Read1.setInitialSize(10);
        ds1Read1.setMaxActive(20);
        DruidDataSource dataSource1Read1 = DruidUtil.getDruidDataSource(ds1Read1);
        dataSourceMap.put("ds_1_read1", dataSource1Read1);

        DruidDataSourceProperties ds1Read2 = new DruidDataSourceProperties();
        ds1Read2.setUrl("127.0.0.1:3306/ds_1_read2");
        ds1Read2.setUsername("root");
        ds1Read2.setPassword("");
        ds1Read2.setMinIdle(10);
        ds1Read2.setInitialSize(10);
        ds1Read2.setMaxActive(20);
        DruidDataSource dataSource1Read2 = DruidUtil.getDruidDataSource(ds1Read2);
        dataSourceMap.put("ds_1_read2", dataSource1Read2);

        DruidDataSourceProperties ds2 = new DruidDataSourceProperties();
        ds2.setUrl("127.0.0.1:3306/ds_2");
        ds2.setUsername("root");
        ds2.setPassword("");
        ds2.setMinIdle(10);
        ds2.setInitialSize(10);
        ds2.setMaxActive(20);
        DruidDataSource dataSource2 = DruidUtil.getDruidDataSource(ds2);
        dataSourceMap.put("ds_2", dataSource2);

        DruidDataSourceProperties ds2Read = new DruidDataSourceProperties();
        ds2Read.setUrl("127.0.0.1:3306/ds_2_read");
        ds2Read.setUsername("root");
        ds2Read.setPassword("");
        ds2Read.setMinIdle(10);
        ds2Read.setInitialSize(10);
        ds2Read.setMaxActive(20);
        DruidDataSource dataSource2Read = DruidUtil.getDruidDataSource(ds2Read);
        dataSourceMap.put("ds_2_read", dataSource2Read);

        DruidDataSourceProperties ds3 = new DruidDataSourceProperties();
        ds3.setUrl("127.0.0.1:3306/ds_3");
        ds3.setUsername("root");
        ds3.setPassword("");
        ds3.setMinIdle(10);
        ds3.setInitialSize(10);
        ds3.setMaxActive(20);
        DruidDataSource dataSource3 = DruidUtil.getDruidDataSource(ds3);
        dataSourceMap.put("ds_3", dataSource3);

        DruidDataSourceProperties ds3Read = new DruidDataSourceProperties();
        ds3Read.setUrl("127.0.0.1:3306/ds_3_read");
        ds3Read.setUsername("root");
        ds3Read.setPassword("");
        ds3Read.setMinIdle(10);
        ds3Read.setInitialSize(10);
        ds3Read.setMaxActive(20);
        DruidDataSource dataSource3Read = DruidUtil.getDruidDataSource(ds3Read);
        dataSourceMap.put("ds_3_read", dataSource3Read);

        return dataSourceMap;
    }

    public static DataSource getDataSource(Map<String, DataSource> dataSourceMap) throws SQLException {
        Map<String, AlgorithmConfiguration> algorithmConfigMap = new HashMap<>(1);
        algorithmConfigMap.put("round_robin", new AlgorithmConfiguration("ROUND_ROBIN", new Properties()));

        ReadwriteSplittingDataSourceRuleConfiguration ds1RuleConfiguration = new ReadwriteSplittingDataSourceRuleConfiguration(
                "ds0", "ds_1", CollectionUtil.newArrayList("ds_1_read1", "ds_1_read2"), TransactionalReadQueryStrategy.PRIMARY, "round_robin");

        ReadwriteSplittingDataSourceRuleConfiguration ds2RuleConfiguration = new ReadwriteSplittingDataSourceRuleConfiguration(
                "ds1", "ds_2", CollectionUtil.newArrayList("ds_2_read"), TransactionalReadQueryStrategy.PRIMARY, "round_robin");

        ReadwriteSplittingDataSourceRuleConfiguration ds3RuleConfiguration = new ReadwriteSplittingDataSourceRuleConfiguration(
                "ds2", "ds_3", CollectionUtil.newArrayList("ds_3_read"), TransactionalReadQueryStrategy.PRIMARY, "round_robin");

        List<RuleConfiguration> ruleConfigurations = CollectionUtil.newArrayList();

        ReadwriteSplittingRuleConfiguration readwriteSplittingRuleConfiguration = new ReadwriteSplittingRuleConfiguration(CollectionUtil.newArrayList(ds1RuleConfiguration, ds2RuleConfiguration, ds3RuleConfiguration), algorithmConfigMap);
        ruleConfigurations.add(readwriteSplittingRuleConfiguration);

        ShardingRuleConfiguration shardingRuleConfiguration = createShardingRuleConfiguration();
        ruleConfigurations.add(shardingRuleConfiguration);

        Properties props = new Properties();
        props.setProperty("sql-show", Boolean.TRUE.toString());

        return ShardingSphereDataSourceFactory.createDataSource("wxjj", createModeConfiguration(), dataSourceMap, ruleConfigurations, props);
    }

    private static ShardingRuleConfiguration createShardingRuleConfiguration() {
        ShardingRuleConfiguration result = new ShardingRuleConfiguration();
        result.getTables().add(getOrderTableRuleConfiguration());

        Properties dbProps = new Properties();
        dbProps.setProperty("algorithm-expression", "ds${operator % 3}");
        result.getShardingAlgorithms().put("db_shard_operator", new AlgorithmConfiguration("INLINE", dbProps));

        Properties tableProps = new Properties();
        tableProps.setProperty("algorithm-expression", "sku_order_${order_id % 3}");
        result.getShardingAlgorithms().put("table_shard_order_id", new AlgorithmConfiguration("INLINE", tableProps));

        Properties dbComplexProps = new Properties();
        dbComplexProps.setProperty("strategy", "COMPLEX");
        dbComplexProps.setProperty("algorithmClassName", "com.scy.db.shard.DbComplexKeysShardingAlgorithm");
        result.getShardingAlgorithms().put("db_complex", new AlgorithmConfiguration("CLASS_BASED", dbComplexProps));

        Properties commonDbProps = new Properties();
        commonDbProps.setProperty("strategy", "HINT");
        commonDbProps.setProperty("algorithmClassName", "com.scy.db.shard.CommonDbHintShardingAlgorithm");
        result.getShardingAlgorithms().put("common_db_hint", new AlgorithmConfiguration("CLASS_BASED", commonDbProps));

        Properties commonTableProps = new Properties();
        commonTableProps.setProperty("strategy", "HINT");
        commonTableProps.setProperty("algorithmClassName", "com.scy.db.shard.CommonTableHintShardingAlgorithm");
        result.getShardingAlgorithms().put("common_table_hint", new AlgorithmConfiguration("CLASS_BASED", commonTableProps));

        result.getKeyGenerators().put("snowflake", new AlgorithmConfiguration("SNOWFLAKE", new Properties()));

        result.getAuditors().put("sharding_key_required_auditor", new AlgorithmConfiguration("DML_SHARDING_CONDITIONS", new Properties()));

        /* SHARDINGSPHERE_HINT: DISABLE_AUDIT_NAMES = sharding_key_required_auditor */
        result.setDefaultAuditStrategy(new ShardingAuditStrategyConfiguration(Collections.singleton("sharding_key_required_auditor"), Boolean.TRUE));
        return result;
    }

    private static ShardingTableRuleConfiguration getOrderTableRuleConfiguration() {
        ShardingTableRuleConfiguration result = new ShardingTableRuleConfiguration("sku_order", "ds${0..2}.sku_order_${0..2}");

        result.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("operator", "db_shard_operator"));

        result.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "table_shard_order_id"));

//        result.setDatabaseShardingStrategy(new HintShardingStrategyConfiguration("common_db_hint"));
//
//        result.setTableShardingStrategy(new HintShardingStrategyConfiguration("common_table_hint"));

//        result.setDatabaseShardingStrategy(new ComplexShardingStrategyConfiguration("order_id, operator", "db_complex"));

        return result;
    }
}
