package com.example.solid.dip.good;

public interface EmailSender {

    void sendEmail(String address, String subject, String body);
}

