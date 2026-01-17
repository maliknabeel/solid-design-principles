package com.example.solid.srp.good;

public class UserService {

    private final UserRepository userRepository;
    private final NotificationSender notificationSender;
    private final AuditLogger auditLogger;

    public UserService(UserRepository userRepository, NotificationSender notificationSender, AuditLogger auditLogger) {
        this.userRepository = userRepository;
        this.notificationSender = notificationSender;
        this.auditLogger = auditLogger;
    }

    public void createUser(String name, String email) {
        User user = new User(name, email);
        userRepository.save(user);
        notificationSender.sendWelcomeNotification(user);
        auditLogger.logUserCreated(user);
    }
}

