package com.pivotallabs.lunchtime.controller;

import com.pivotallabs.lunchtime.integration.ControllerTestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
public class PersonControllerTest extends ControllerTestBase {

    @Test
    public void personIndex_shouldHaveFormToAddAPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("//form[@method='post']").exists())
                .andExpect(MockMvcResultMatchers.xpath("//form/input[@name='email']").exists())
                .andExpect(MockMvcResultMatchers.xpath("//form/button[@type='submit']").string("Add person"));
    }

    @Test
    public void personCreate_shouldAddPeople() throws Exception {
        mockMvc.perform(post("/person")
                .param("email", "darth@darkside.org"))
                .andExpect(redirectedUrl("/person"));

        assertThat(countRowsInTable("PERSON", "email='darth@darkside.org' AND id != 0")).isEqualTo(1);
    }

    @Test
    public void personCreate_withEmptyEmail_shouldFail() throws Exception {
        mockMvc.perform(post("/person").param("email", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void personCreate_withNullEmail_shouldFail() throws Exception {
        mockMvc.perform(post("/person"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(xpath("//ul[@class='errors']/li[text()='may not be empty']").exists());

        assertThat(countRowsInTable("PERSON")).isEqualTo(0);
    }
}
