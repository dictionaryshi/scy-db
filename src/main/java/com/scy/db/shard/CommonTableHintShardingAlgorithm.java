package com.scy.db.shard;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author : shichunyang
 * Date    : 2023/5/9
 * Time    : 10:06 上午
 * ---------------------------------------
 * Desc    : CommonTableHintShardingAlgorithm
 */
public class CommonTableHintShardingAlgorithm implements HintShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        Collection<Long> values = shardingValue.getValues();

        availableTargetNames = availableTargetNames.stream().filter(table -> values.stream().anyMatch(value -> table.endsWith("_".concat(String.valueOf(value))))).collect(Collectors.toList());
        return availableTargetNames;
    }
}
