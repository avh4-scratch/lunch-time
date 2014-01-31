package com.pivotallabs.lunchtime;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
//@EnableWebMvc  -   you don't need this. -Josh
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

/**
 * This is a Java configuration class. Spring Boot, to do its magic, simply wires up these
 * objects for you, but most of the time you can simply override the definition it provides
 * We do that here in our provisioning of a javax.sql.DataSource and a JpaVendorAdapter
 */

// -Dspring.active.profiles=production
@Profile("production")  // remind me to explain when you see this
@Configuration
class PostgreSqlConfiguration {

    @Bean(destroyMethod = "close")
    javax.sql.DataSource dataSource(Environment env) {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class.getCanonicalName());
        dataSource.setJdbcUrl(env.getProperty("dataSource.url").trim());
        dataSource.setUser(env.getProperty("dataSource.user"));
        dataSource.setUser(env.getProperty("dataSource.password"));
        dataSource.setMaxConnectionsPerPartition(2);
        return dataSource;
    }

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        return hibernateJpaVendorAdapter;
    }
}
