package com.example.mfa.email.services;

import com.example.mfa.email.dto.EmailRequest;
import com.example.mfa.exception.EmailSendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private static final String EMAIL_SUBJECT = "MFA Code";
    private static final String EMAIL_TEXT = "Your MFA code is %s. It is valid for the next 30 seconds.";

    private JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(EmailRequest request) throws MailException {
        try {
            emailSender.send(getPreparedMessage(request));
        } catch (Exception ex) {
            logger.error("Failed to send email to {}: {}", request.email(), ex.getMessage(), ex);
            throw new EmailSendException(String.format("Failed to send email to %s", request.email()));
        }
    }

    private SimpleMailMessage getPreparedMessage(EmailRequest request) {
        var message = new SimpleMailMessage();
        message.setTo(request.email());
        message.setSubject(EMAIL_SUBJECT);
        message.setText(String.format(EMAIL_TEXT, request.code()));

        return message;
    }
}
