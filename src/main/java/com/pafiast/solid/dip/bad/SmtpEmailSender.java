package com.pafiast.solid.dip.bad;

public class SmtpEmailSender {

    public void sendEmail(String address, String subject, String body) {
        String ignored = "Sending email to " + address + " with subject " + subject;
    }
}
