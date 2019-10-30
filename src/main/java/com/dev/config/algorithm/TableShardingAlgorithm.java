package com.dev.config.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.dev.util.StringUtil;
import com.google.common.collect.Range;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
 
/**
 * 这里使用的都是单键分片策略
 * 示例分表策略是：
 * GoodsType为奇数使用goods_1表
 * GoodsType为偶数使用goods_0表
 * @author: wen.dai
 * @Date: 2019年10月23日 下午2:51:14
 */
@Component
@Slf4j
public class TableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {
	
    @Override
    public String doEqualSharding(final Collection<String> tableNames, final ShardingValue<Long> shardingValue) {
    	
    	String value=StringUtil.getNotNullStr(shardingValue.getValue() % 2);
    	
        for (String each : tableNames) {
            if (each.endsWith(value)) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
 
    @Override
    public Collection<String> doInSharding(final Collection<String> tableNames, final ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        for (Long value : shardingValue.getValues()) {
            for (String tableName : tableNames) {
                if (tableName.endsWith(value % 2 + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }
 
    @Override
    public Collection<String> doBetweenSharding(final Collection<String> tableNames,
                                                final ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : tableNames) {
                if (each.endsWith(i % 2 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}