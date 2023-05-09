package com.scy.db.shard;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;

/**
 * @author : shichunyang
 * Date    : 2023/5/9
 * Time    : 10:06 上午
 * ---------------------------------------
 * Desc    : CommonDbHintShardingAlgorithm
 */
public class CommonDbHintShardingAlgorithm implements HintShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        return availableTargetNames;
    }
}
