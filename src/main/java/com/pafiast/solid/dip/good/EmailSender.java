package com.pafiast.solid.dip.good;

/**
 * Interface for sending emails.
 * High-level modules should depend on this abstraction rather than concrete implementations.
 */
public interface EmailSender {

    /**
     * Sends an email.
     *
     * @param address the recipient address
     * @param subject the email subject
     * @param body    the email body
     */
    void sendEmail(String address, String subject, String body);
}

