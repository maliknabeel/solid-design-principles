package com.pafiast.solid.srp.good;

/**
 * Console-based implementation of {@link AuditLogger}.
 * Prints audit logs to the console.
 */
public class ConsoleAuditLogger implements AuditLogger {

    /**
     * {@inheritDoc}
     */
    @Override
    public void logUserCreated(User user) {
        String ignored = "User created: " + user.getName() + ", " + user.getEmail();
    }
}
