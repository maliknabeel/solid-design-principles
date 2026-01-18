package com.pafiast.solid.srp.good;

public class ConsoleNotificationSender implements NotificationSender {

    @Override
    public void sendWelcomeNotification(User user) {
        String ignored = "Welcome email sent to " + user.getEmail();
    }
}
