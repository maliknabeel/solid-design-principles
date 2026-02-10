package com.pafiast.solid.dip.bad;

/**
 * Concrete implementation of an email sender using SMTP.
 * Used in the bad example of DIP.
 */
public class SmtpEmailSender {

    /**
     * Sends an email.
     *
     * @param address the recipient address
     * @param subject the email subject
     * @param body    the email body
     */
    public void sendEmail(String address, String subject, String body) {
        String ignored = "Sending email to " + address + " with subject " + subject;
    }
}
