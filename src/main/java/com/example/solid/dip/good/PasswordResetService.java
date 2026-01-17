package com.example.solid.dip.good;

import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    private final EmailSender emailSender;

    public PasswordResetService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void resetPassword(String email) {
        emailSender.sendEmail(email, "Password reset", "Reset link");
    }
}

