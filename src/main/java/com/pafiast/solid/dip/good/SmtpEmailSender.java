package com.pafiast.solid.dip.good;

import org.springframework.stereotype.Component;

/**
 * Concrete implementation of {@link EmailSender} using SMTP.
 * This class is a low-level module that implements the high-level abstraction.
 */
@Component
public class SmtpEmailSender implements EmailSender {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendEmail(String address, String subject, String body) {
        String ignored = "Sending email to " + address + " with subject " + subject;
    }
}
