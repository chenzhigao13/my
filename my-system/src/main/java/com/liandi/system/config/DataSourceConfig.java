package com.liandi.system.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.Data;

/**
 * 数据源配置类
 * 
 * @author czg
 * @date 2019/10/21 14:41
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) throws SQLException {
        return buildDruidDataSource(dataSourceProperties);
    }

    private DruidDataSource buildDruidDataSource(DataSourceProperties properties) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());

        druidDataSource.setInitialSize(properties.getInitialSize());
        druidDataSource.setMaxActive(properties.getMaxActive());
        druidDataSource.setMinIdle(properties.getMinIdle());
        druidDataSource.setMaxWait(properties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druidDataSource.setMaxEvictableIdleTimeMillis(properties.getMaxEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(properties.getValidationQuery());
        druidDataSource.setValidationQueryTimeout(properties.getValidationQueryTimeout());
        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        druidDataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
        druidDataSource.setSharePreparedStatements(properties.isSharePreparedStatements());

        druidDataSource.setFilters(properties.getFilters());
        druidDataSource.init();

        return druidDataSource;
    }

    /**
     * 数据源配置信息
     *
     * @author czg
     * @date 2019/10/25 15:01
     */
    @Data
    class DataSourceProperties {

        private String driverClassName;
        private String url;
        private String username;
        private String password;

        /**
         * Druid默认参数
         */
        private int initialSize = 2;
        private int maxActive = 10;
        private int minIdle = -1;
        private long maxWait = 60L * 1000L;
        private long timeBetweenEvictionRunsMillis = 60L * 1000L;
        private long minEvictableIdleTimeMillis = 1000L * 60L * 30L;
        private long maxEvictableIdleTimeMillis = 1000L * 60L * 60L * 7L;
        private String validationQuery = "select 1";
        private int validationQueryTimeout = -1;
        private boolean testOnBorrow = false;
        private boolean testOnReturn = false;
        private boolean testWhileIdle = true;
        private boolean poolPreparedStatements = false;
        private int maxOpenPreparedStatements = -1;
        private boolean sharePreparedStatements = false;
        private String filters = "stat,wall";

    }

}
