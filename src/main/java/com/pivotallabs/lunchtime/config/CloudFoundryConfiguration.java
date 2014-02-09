package com.pivotallabs.lunchtime.config;

import com.googlecode.flyway.core.Flyway;
import com.jolbox.bonecp.BoneCPDataSource;
import org.postgresql.Driver;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.common.PostgresqlServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("cloud")
public class CloudFoundryConfiguration {
    @Bean
    public Cloud cloud() {
        return new CloudFactory().getCloud();
    }

    @Bean(destroyMethod = "close")
    public BoneCPDataSource cloudDataSource() {
        PostgresqlServiceInfo serviceInfo = (PostgresqlServiceInfo) cloud().getServiceInfo("lunch-time-psql");

        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(Driver.class.getCanonicalName());
        dataSource.setJdbcUrl(serviceInfo.getJdbcUrl());
        dataSource.setMaxConnectionsPerPartition(2);

        return dataSource;
    }

    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
        return flyway;
    }
}
