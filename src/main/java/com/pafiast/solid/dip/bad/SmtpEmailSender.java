package com.pafiast.solid.dip.good;

/**
 * Concrete implementation of an email sender using SMTP.
 * Implements the EmailSender abstraction.
 */
public class SmtpEmailSender implements EmailSender {

    /**
     * Sends an email via SMTP.
     *
     * @param address the recipient address
     * @param subject the email subject
     * @param body    the email body
     */
    @Override
    public void sendEmail(String address, String subject, String body) {
        String ignored = "Sending email to " + address + " with subject " + subject;
    }
}
