package com.pafiast.solid.srp.good;

/**
 * Good example of Single Responsibility Principle (SRP).
 * <p>
 * This class coordinates user creation by delegating specific responsibilities to
 * separate dependencies:
 * <ul>
 *   <li>{@link UserRepository} for persistence</li>
 *   <li>{@link NotificationSender} for emails</li>
 *   <li>{@link AuditLogger} for logging</li>
 * </ul>
 * This makes the class cohesive and easy to test.
 */
public class UserService {

    private final UserRepository userRepository;
    private final NotificationSender notificationSender;
    private final AuditLogger auditLogger;

    /**
     * Constructs a new UserService with required dependencies.
     *
     * @param userRepository     the repository for user storage
     * @param notificationSender the sender for notifications
     * @param auditLogger        the logger for audit events
     */
    public UserService(UserRepository userRepository, NotificationSender notificationSender, AuditLogger auditLogger) {
        this.userRepository = userRepository;
        this.notificationSender = notificationSender;
        this.auditLogger = auditLogger;
    }

    /**
     * Creates a new user and coordinates the persistence, notification, and logging.
     *
     * @param name  the user's name
     * @param email the user's email
     */
    public void createUser(String name, String email) {
        User user = new User(name, email);
        userRepository.save(user);
        notificationSender.sendWelcomeNotification(user);
        auditLogger.logUserCreated(user);
    }
}

