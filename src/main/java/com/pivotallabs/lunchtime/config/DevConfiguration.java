package com.pivotallabs.lunchtime.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
public class DevConfiguration {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class.getCanonicalName());
        dataSource.setJdbcUrl("jdbc:postgresql://localhost/lunch_time");
        dataSource.setUser("lunch_time");
        dataSource.setPassword("password");
        dataSource.setMaxConnectionsPerPartition(2);
        return dataSource;
    }
}
