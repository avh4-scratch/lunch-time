package com.pivotallabs.lunchtime.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;

import javax.sql.DataSource;

@Profile("integration_test")
@Configuration
public class IntegrationTestConfiguration {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class.getCanonicalName());
        dataSource.setJdbcUrl("jdbc:postgresql://localhost/lunch_time_integration_test");
        dataSource.setUser("lunch_time_integration_test");
        dataSource.setPassword("password");
        dataSource.setMaxConnectionsPerPartition(2);
        return dataSource;
    }
}
