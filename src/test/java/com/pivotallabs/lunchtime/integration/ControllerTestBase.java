package com.pivotallabs.lunchtime.integration;

import com.pivotallabs.lunchtime.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class ControllerTestBase {
    protected MockMvc mockMvc;
    @Inject protected JdbcTemplate jdbcTemplate;
    @Inject private WebApplicationContext wac;

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.<StandaloneMockMvcBuilder>webAppContextSetup(wac).build();
    }

    protected int countRowsInTable(String tableName) {
        return JdbcTestUtils.countRowsInTable(jdbcTemplate, tableName);
    }

    protected int countRowsInTable(String tableName, String whereClause) {
        return JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, tableName, whereClause);
    }
}
