package com.pivotallabs.lunchtime.test.integration;

import com.pivotallabs.lunchtime.jobs.MatchingService;
import com.pivotallabs.lunchtime.repository.PersonRepository;
import com.pivotallabs.lunchtime.test.support.IntegrationTestBase;
import com.pivotallabs.lunchtime.test.support.MockMailSender;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonIntegrationTest extends IntegrationTestBase {

    @Inject private MatchingService matchingService;
    @Inject private MockMailSender mailSender;
    @Inject private PersonRepository personRepository;

    @Before
    public void setUp() {
        getContext().getAutowireCapableBeanFactory().autowireBean(this);
        personRepository.deleteAll();
    }

    @Test
    public void testAddPerson() throws Exception {
        goTo(getServerUrl() + "/");

        fill("input").with("luke@lightside.com");
        click("button[type=submit]");
        await().until("button[type=submit]").areEnabled();

        assertThat(find("input").getValue()).isEmpty();
    }

    @Test
    public void testMakingAMatch() throws Exception {
        goTo(getServerUrl() + "/");
        fill("input").with("luke@lightside.com");
        click("button[type=submit]");
        assertThat(personRepository.count()).isEqualTo(1);

        goTo(getServerUrl() + "/");
        fill("input").with("darth@darkside.org");
        click("button[type=submit]");
        assertThat(personRepository.count()).isEqualTo(2);

        // TODO invoke this by making the real scheduler think it's 11:30am on a weekday
        matchingService.makeMatches();

        assertThat(mailSender.getMessage()).isNotNull();
        assertThat(mailSender.getMessage().getTo())
                .contains("luke@lightside.com", "darth@darkside.org");
        assertThat(mailSender.getMessage().getSubject()).isEqualTo("It's lunch time, kids!");
    }
}
