package com.pivotallabs.lunchtime.jobs;

import com.pivotallabs.lunchtime.model.Match;
import com.pivotallabs.lunchtime.model.Matcher;
import com.pivotallabs.lunchtime.model.Person;
import com.pivotallabs.lunchtime.test.support.MockMailSender;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailSender;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.stub;

public class MatchingServiceTest {
    @Mock private Matcher matcher;
    @Mock private Person p1;
    @Mock private Person p2;
    private MatchingService subject;
    private MockMailSender mailSender;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        stub(p1.getEmail()).toReturn("lex@luther.name");
        stub(p2.getEmail()).toReturn("bruce@super.ma");
        mailSender = new MockMailSender();
        subject = new MatchingService(mailSender, matcher);
    }

    @Test
    public void whenAMatchIsAvailable_sendsAnEmailToBothRecipients() throws Exception {
        stub(matcher.getMatch()).toReturn(new Match(p1, p2));
        subject.makeMatches();
        assertThat(mailSender.getMessage().getTo()).contains("lex@luther.name", "bruce@super.ma");
        assertThat(mailSender.getMessage().getSubject()).isEqualTo("It's lunch time, kids!");
    }

    @Test
    public void whenNoMatchIsAvailable_doesNotSendEmail() throws Exception {
        stub(matcher.getMatch()).toReturn(null);
        subject.makeMatches();
        assertThat(mailSender.getMessage()).isNull();
    }
}
