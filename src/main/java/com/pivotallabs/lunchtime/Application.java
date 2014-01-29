package com.pivotallabs.lunchtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan
@EnableAutoConfiguration
//@EnableJpaRepositories
@EnableWebMvc
public class Application {
//
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(H2)
//                .build();
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
