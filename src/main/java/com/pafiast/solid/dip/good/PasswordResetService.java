package com.pafiast.solid.dip.good;

import org.springframework.stereotype.Service;

/**
 * Good example of Dependency Inversion Principle (DIP).
 * <p>
 * This class follows DIP by depending on the {@link EmailSender} abstraction.
 * The concrete implementation is injected, allowing for loose coupling and easier testing.
 */
@Service
public class PasswordResetService {

    private final EmailSender emailSender;

    /**
     * Constructs a new PasswordResetService with the given email sender.
     *
     * @param emailSender the email sender to use
     */
    public PasswordResetService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * Resets the password for a user.
     *
     * @param email the user's email
     */
    public void resetPassword(String email) {
        emailSender.sendEmail(email, "Password reset", "Reset link");
    }
}

