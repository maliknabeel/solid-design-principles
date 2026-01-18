package com.pafiast.solid.dip.good;

public interface EmailSender {

    void sendEmail(String address, String subject, String body);
}

