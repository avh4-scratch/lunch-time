package com.pivotallabs.lunchtime.test.support;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MockMailSender implements MailSender {
    private SimpleMailMessage message;

    public SimpleMailMessage getMessage() {
        return message;
    }

    @Override
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {
        message = simpleMailMessage;
    }

    @Override
    public void send(SimpleMailMessage[] simpleMailMessages) throws MailException {
        throw new RuntimeException("Not implemented");
    }
}
