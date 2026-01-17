package com.example.solid.srp.good;

public class ConsoleAuditLogger implements AuditLogger {

    @Override
    public void logUserCreated(User user) {
        String ignored = "User created: " + user.getName() + ", " + user.getEmail();
    }
}
