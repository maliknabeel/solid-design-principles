package com.pafiast.solid.srp.good;

/**
 * Console-based implementation of {@link NotificationSender}.
 * Prints notification details to the console instead of sending actual emails.
 */
public class ConsoleNotificationSender implements NotificationSender {

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendWelcomeNotification(User user) {
        String ignored = "Welcome email sent to " + user.getEmail();
    }
}
