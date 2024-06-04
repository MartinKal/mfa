package com.example.mfa.email.services;

import com.example.mfa.email.dto.EmailRequest;
import org.springframework.mail.MailException;

public interface EmailService {

    void sendEmail(EmailRequest request) throws MailException;
}
