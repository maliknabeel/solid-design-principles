package com.pafiast.solid.dip.bad;

public class PasswordResetService {

    public void resetPassword(String email) {
        SmtpEmailSender smtpEmailSender = new SmtpEmailSender();
        smtpEmailSender.sendEmail(email, "Password reset", "Reset link");
    }
}

