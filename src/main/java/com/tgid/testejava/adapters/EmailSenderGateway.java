package com.tgid.testejava.adapters;

public interface EmailSenderGateway {
    void sendEmail(String toEmail, String subject, String body);
}
