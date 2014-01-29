package com.pivotallabs.lunchtime.integration;

import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

public class IntegrationTest extends IntegrationTestBase {

//    @Test
//    public void shouldBeAbleToAddPeople() throws Exception {
//        mockMvc.perform(post("/person").param("email", "darth@darkside.org"))
//                .andExpect(status().isCreated());
//
//        assertThat(countRowsInTable("PERSON")).isEqualTo(1);
//    }

    @Test
    public void personIndex_shouldHaveFormToAddAPerson() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(content().string("Add person"));
    }

}
