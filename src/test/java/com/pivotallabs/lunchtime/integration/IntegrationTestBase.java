package com.pivotallabs.lunchtime.integration;

import com.pivotallabs.lunchtime.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class IntegrationTestBase {
    protected MockMvc mockMvc;
//    protected JdbcTemplate jdbcTemplate;
//    @Inject private DataSource dataSource;
    @Inject private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.<StandaloneMockMvcBuilder>webAppContextSetup(wac).build();
//        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected int countRowsInTable(String tableName) {
//        return JdbcTestUtils.countRowsInTable(jdbcTemplate, tableName);
        return -1;
    }
}
