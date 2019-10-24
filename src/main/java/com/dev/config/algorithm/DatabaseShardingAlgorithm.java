package com.dev.config.algorithm;
import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.dev.config.Database0Config;
import com.dev.config.Database1Config;
import com.google.common.collect.Range;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
import java.util.Collection;
import java.util.LinkedHashSet;
 
/**
 * 这里使用的都是单键分片策略
 * 示例分库策略是：
 * GoodsId<=20使用database0库
 * 其余使用database1库
 * @author dev
 * @Date: 2019年10月23日 下午2:47:31
 */
@Component
@Slf4j
public class DatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {
 
    @Autowired
    private Database0Config database0Config;
 
    @Autowired
    private Database1Config database1Config;
 
    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
    	log.info("=======doEqualSharding===========");
    	log.info("======availableTargetNames======="+availableTargetNames);
    	log.info("======shardingValue======="+shardingValue);
    	log.info("------------------------------------------------------------------------------------");
        Long value = shardingValue.getValue();
        if (value <= 20L) {
            return database0Config.getDatabaseName();
        } else {
            return database1Config.getDatabaseName();
        }
    }
 
    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
    	log.info("=======doInSharding===========");
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Long value : shardingValue.getValues()) {
            if (value <= 20L) {
                result.add(database0Config.getDatabaseName());
            } else {
                result.add(database1Config.getDatabaseName());
            }
        }
        return result;
    }
 
    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,ShardingValue<Long> shardingValue) {
    	log.info("=======doBetweenSharding===========");
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long value = range.lowerEndpoint(); value <= range.upperEndpoint(); value++) {
            if (value <= 20L) {
                result.add(database0Config.getDatabaseName());
            } else {
                result.add(database1Config.getDatabaseName());
            }
        }
        return result;
    }
}