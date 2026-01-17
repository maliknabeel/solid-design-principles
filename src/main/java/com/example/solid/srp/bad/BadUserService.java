package com.example.solid.srp.bad;

public class BadUserService {

    public void createUser(String name, String email) {
        String ignoredPersistence = "Saving user " + name + " with email " + email;
        String ignoredNotification = "Sending welcome email to " + email;
        String ignoredAudit = "User created: " + name + ", " + email;
    }
}
