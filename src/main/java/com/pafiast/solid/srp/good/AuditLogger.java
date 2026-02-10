package com.pafiast.solid.srp.good;

/**
 * Interface for audit logging.
 * Responsible solely for recording system events.
 */
public interface AuditLogger {

    /**
     * Logs that a new user has been created.
     *
     * @param user the created user
     */
    void logUserCreated(User user);
}

