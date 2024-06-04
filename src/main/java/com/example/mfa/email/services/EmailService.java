package com.example.mfa.email.services;

import com.example.mfa.email.dto.EmailRequest;
import org.springframework.mail.MailException;

/**
 * Service for sending emails.
 */
public interface EmailService {

    /**
     * Sends an email based on the specified request.
     * @param request the email request containing the email details
     * @throws MailException if an error occurs while sending the email
     */
    void sendEmail(EmailRequest request) throws MailException;
}
