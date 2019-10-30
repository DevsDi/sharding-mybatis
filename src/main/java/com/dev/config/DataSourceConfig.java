package com.dev.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import com.dev.config.algorithm.DatabaseShardingAlgorithm;
import com.dev.config.algorithm.TableShardingAlgorithm;

/** 
 * @ClassName: DataSourceConfig
 * @description: 
 * @author: wen.dai
 * @Date: 2019年10月23日 下午2:51:26
 */ 
@Configuration
public class DataSourceConfig {
 
    @Autowired
    private Database0Config database0Config;
 
    @Autowired
    private Database1Config database1Config;
 
    @Autowired
    private DatabaseShardingAlgorithm databaseShardingAlgorithm;
 
    @Autowired
    private TableShardingAlgorithm tableShardingAlgorithm;
    
    @Autowired
    private Environment environment;
 
    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }
 
    private DataSource buildDataSource() throws SQLException {
    	
    	String logicTable=environment.getProperty("logic.table.name");
    	String actualTables=environment.getProperty("actual.table.name");
    	
    	List<String> tableList=Arrays.asList(actualTables.split(","));
    	
    	String databaseShardingColumn=environment.getProperty("database.sharding.column");
    	String tableShardingColumn=environment.getProperty("table.sharding.column");
    	
        //分库设置
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        //添加两个数据库database0和database1
        dataSourceMap.put(database0Config.getDatabaseName(), database0Config.createDataSource());
        dataSourceMap.put(database1Config.getDatabaseName(), database1Config.createDataSource());
        //设置默认数据库
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, database0Config.getDatabaseName());
 
        //分表设置，大致思想就是将查询虚拟表Goods根据一定规则映射到真实表中去
        TableRule orderTableRule = TableRule.builder(logicTable)
                .actualTables(tableList)
                .dataSourceRule(dataSourceRule)
                .build();
 
        //分库分表策略
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(orderTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy(databaseShardingColumn, databaseShardingAlgorithm))
                .tableShardingStrategy(new TableShardingStrategy(tableShardingColumn, tableShardingAlgorithm)).build();
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
        return dataSource;
    }
 
 
    @Bean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }
 
}
