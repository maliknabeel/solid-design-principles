package com.pafiast.solid.dip.good;

/**
 * Abstraction for email sending functionality.
 */
public interface EmailSender {
    void sendEmail(String address, String subject, String body);
}
