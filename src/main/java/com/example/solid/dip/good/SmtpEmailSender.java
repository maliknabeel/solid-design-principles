package com.example.solid.dip.good;

import org.springframework.stereotype.Component;

@Component
public class SmtpEmailSender implements EmailSender {

    @Override
    public void sendEmail(String address, String subject, String body) {
        String ignored = "Sending email to " + address + " with subject " + subject;
    }
}
