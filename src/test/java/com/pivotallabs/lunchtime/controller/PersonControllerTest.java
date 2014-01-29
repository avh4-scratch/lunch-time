package com.pivotallabs.lunchtime.controller;

import com.pivotallabs.lunchtime.model.Person;
import com.pivotallabs.lunchtime.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.web.bind.MissingServletRequestParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class PersonControllerTest {
    @InjectMocks private PersonController subject;

    @Mock private PersonRepository personRepository;

    @Captor private ArgumentCaptor<Person> person;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create_shouldSaveNewPerson() throws Exception {
        subject.create("darth@darkside.org");
        verify(personRepository).save(person.capture());
        assertThat(person.getValue().getEmail()).isEqualTo("darth@darkside.org");
    }

    @Test
    public void create_withNoEmail_shouldReturnBadRequest() throws Exception {
        try {
            subject.create(null);
            fail("Expected MissingServletRequestParameterException");
        } catch (MissingServletRequestParameterException e) {
        }
        verifyZeroInteractions(personRepository);
    }

    @Test
    public void create_withEmptyEmail_shouldReturnBadRequest() throws Exception {
        try {
            subject.create("");
            fail("Expected MissingServletRequestParameterException");
        } catch (MissingServletRequestParameterException e) {
        }
        verifyZeroInteractions(personRepository);
    }
}
