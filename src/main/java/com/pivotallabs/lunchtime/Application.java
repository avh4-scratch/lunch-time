package com.pivotallabs.lunchtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableWebMvc
public class Application {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(H2)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
