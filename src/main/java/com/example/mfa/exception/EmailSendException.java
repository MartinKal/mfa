package com.example.mfa.exception;

import org.springframework.mail.MailException;

public class EmailSendException extends MailException {
    public EmailSendException(String msg) {
        super(msg);
    }

    public EmailSendException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
