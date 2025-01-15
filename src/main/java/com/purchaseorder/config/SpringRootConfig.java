package com.purchaseorder.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan({"com.purchaseorder"})
public class SpringRootConfig {

    @Bean(name = "dataSource")
    @Primary
    public HikariDataSource dataSource() {
        String poolName = "purchaseorder-pool";
        String serverName = "localhost";
        String dbName = "db_purchase_order";
        String username = "root";
        String password = "Adhitya2025";

        return createDataSource(poolName, serverName, dbName, username, password, 10);
    }

    private HikariDataSource createDataSource(String poolName, String serverName, String dbName, String username, String password, int maxPoolSize) {
        HikariConfig config = new HikariConfig();
        config.setPoolName(poolName);
        config.setJdbcUrl("jdbc:mysql://" + serverName + ":3306/" + dbName);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(maxPoolSize);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");

        return new HikariDataSource(config);
    }
}
