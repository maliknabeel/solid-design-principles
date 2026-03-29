package com.pafiast.solid.dip.good;

/**
 * Good example of Dependency Inversion Principle (DIP).
 * <p>
 * This class follows DIP because it depends on abstraction (EmailSender interface)
 * rather than concrete implementation. The email sender is injected through constructor.
 */
public class PasswordResetService {

    private final EmailSender emailSender;

    /**
     * Constructor with dependency injection.
     *
     * @param emailSender the email sender implementation
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
