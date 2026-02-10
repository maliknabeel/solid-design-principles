package com.pafiast.solid.dip.bad;

/**
 * Bad example of Dependency Inversion Principle (DIP).
 * <p>
 * This class violates DIP because it directly instantiates the concrete class {@link SmtpEmailSender}.
 * This tight coupling makes it difficult to switch to a different email provider or mock the email sender for testing.
 */
public class PasswordResetService {

    /**
     * Resets the password for a user.
     *
     * @param email the user's email
     */
    public void resetPassword(String email) {
        SmtpEmailSender smtpEmailSender = new SmtpEmailSender();
        smtpEmailSender.sendEmail(email, "Password reset", "Reset link");
    }
}

