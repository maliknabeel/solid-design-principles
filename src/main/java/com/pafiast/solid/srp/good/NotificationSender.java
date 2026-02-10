package com.pafiast.solid.srp.good;

/**
 * Interface for sending notifications.
 * Responsible solely for handling communication logic.
 */
public interface NotificationSender {

    /**
     * Sends a welcome notification to the user.
     *
     * @param user the recipient user
     */
    void sendWelcomeNotification(User user);
}

