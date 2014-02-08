package com.pivotallabs.lunchtime.controller;

import com.pivotallabs.lunchtime.test.support.ControllerTestBase;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class PersonControllerTest extends ControllerTestBase {

    @Test
    public void create_shouldAddPeople() throws Exception {
        mockMvc.perform(post("/person")
                .param("email", "darth@darkside.org"))
                .andExpect(status().isCreated());

        assertThat(countRowsInTable("PERSON", "email='darth@darkside.org' AND id != 0")).isEqualTo(1);

        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].email").value("darth@darkside.org"));
    }

    @Test
    public void show_shouldShowTheEmailForTheUser() throws Exception {
        mockMvc.perform(post("/person").param("email", "darth@darkside.org"));
        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("darth@darkside.org"));
    }

    @Test
    public void create_withNullEmail_shouldFail() throws Exception {
        mockMvc.perform(post("/person"))
                .andDo(print())
                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.messages.email[0]").value("may not be empty"))
        ;

        assertThat(countRowsInTable("PERSON")).isEqualTo(0);
    }
}
