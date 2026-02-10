package com.pafiast.solid.srp.bad;

/**
 * Bad example of Single Responsibility Principle (SRP).
 * <p>
 * This class violates SRP because it handles multiple responsibilities:
 * <ul>
 *   <li>User persistence (saving to database)</li>
 *   <li>Notification (sending email)</li>
 *   <li>Auditing (logging actions)</li>
 * </ul>
 * A change in any of these areas would require modifying this class.
 */
public class BadUserService {

    /**
     * Creates a user and handles all associated side effects.
     *
     * @param name  the user's name
     * @param email the user's email
     */
    public void createUser(String name, String email) {
        String ignoredPersistence = "Saving user " + name + " with email " + email;
        String ignoredNotification = "Sending welcome email to " + email;
        String ignoredAudit = "User created: " + name + ", " + email;
    }
}
