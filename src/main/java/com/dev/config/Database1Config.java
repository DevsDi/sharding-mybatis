package com.dev.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
 
import javax.sql.DataSource;
 

/** 
 * @ClassName: Database1Config
 * @description: 
 * @author: wen.dai
 * @Date: 2019年10月23日 下午2:47:31
 */ 
@Data
@ConfigurationProperties(prefix = "database1")
@Component
public class Database1Config {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String databaseName;
 
    public DataSource createDataSource() {
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName(getDriverClassName());
        result.setUrl(getUrl());
        result.setUsername(getUsername());
        result.setPassword(getPassword());
        return result;
    }
}