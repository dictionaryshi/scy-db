package com.scy.db.shard;

import com.scy.core.snowflake.Sequence;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2023/5/10
 * Time    : 12:41 下午
 * ---------------------------------------
 * Desc    : DbComplexKeysShardingAlgorithm
 */
public class DbComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        Map<String, Collection<Long>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        Collection<Long> orderIds = columnNameAndShardingValuesMap.get("order_id");
        List<Long> bizs = orderIds.stream().map(Sequence::getBiz).collect(Collectors.toList());

        availableTargetNames = availableTargetNames.stream().filter(db -> bizs.stream().anyMatch(biz -> Objects.equals(db, "ds".concat(String.valueOf(biz))))).collect(Collectors.toList());
        return availableTargetNames;
    }
}
