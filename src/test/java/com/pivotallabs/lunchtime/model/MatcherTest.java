package com.pivotallabs.lunchtime.model;

import com.pivotallabs.lunchtime.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.stub;

public class MatcherTest {
    private Matcher subject;
    @Mock private PersonRepository personRepository;
    @Mock private Person p1;
    @Mock private Person p2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subject = new Matcher(personRepository);
    }

    @Test
    public void withTwoPeople_matchesThem() throws Exception {
        stub(personRepository.findAll()).toReturn(Arrays.asList(p1, p2));
        assertThat(subject.getMatch()).isEqualTo(new Match(p1, p2));
    }

    @Test
    public void withNoPeople_givesNoMatch() throws Exception {
        stub(personRepository.findAll()).toReturn(Arrays.<Person>asList());
        assertThat(subject.getMatch()).isNull();
    }
}
