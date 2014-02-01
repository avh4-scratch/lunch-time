package com.pivotallabs.lunchtime.controller;

import com.pivotallabs.lunchtime.test.support.ControllerTestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
public class PersonControllerTest extends ControllerTestBase {

    @Test
    public void personIndex_shouldHaveFormToAddAPerson() throws Exception {
        mockMvc.perform(get("/person"))
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

        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(xpath("//ul/li/i[text()='darth@darkside.org']").exists());
    }

    @Test
    public void show_shouldShowTheEmailForTheUser() throws Exception {
        mockMvc.perform(post("/person").param("email", "darth@darkside.org"));
        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(xpath("//div[text()='darth@darkside.org']").exists());
    }

    @Test
    public void personCreate_withNullEmail_shouldFail() throws Exception {
        mockMvc.perform(post("/person"))
                .andExpect(status().isBadRequest())
                .andExpect(xpath("//ul[@class='errors']/li[text()='may not be empty']").exists());

        assertThat(countRowsInTable("PERSON")).isEqualTo(0);
    }
}
