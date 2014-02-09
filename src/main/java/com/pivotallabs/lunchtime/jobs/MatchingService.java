package com.pivotallabs.lunchtime.jobs;

import com.pivotallabs.lunchtime.model.Match;
import com.pivotallabs.lunchtime.model.Matcher;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;

@Component("matchingService")
public class MatchingService {
    private final MailSender mailSender;
    private final Matcher matcher;

    @Inject
    public MatchingService(MailSender mailSender, Matcher matcher) {
        this.mailSender = mailSender;
        this.matcher = matcher;
    }

//    @Scheduled(cron = "*/5 * * * * ?")
    @Scheduled(fixedRate = 50000)
    public void test() {
        System.out.println("TEST!! " + new Date());
    }

    @Scheduled(cron = "0 30 11 ? * MON-FRI *")
    public void makeMatches() {
        Match match = matcher.getMatch();
        if (match != null) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(new String[]{match.getFirst().getEmail(), match.getSecond().getEmail()});
            mail.setText("This is your match for lunch.  Have a good time!");
            mail.setSubject("It's lunch time, kids!");
            mailSender.send(mail);
        }
    }
}
