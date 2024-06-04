package com.example.mfa.email.services;

import com.example.mfa.email.dto.EmailRequest;
import com.example.mfa.exception.EmailSendException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmailServiceImplTest {

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendEmail() {
        EmailRequest request = new EmailRequest("123456", "asd@gmail.com");

        emailService.sendEmail(request);

        verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testSendEmailThrowsException() {
        EmailRequest request = new EmailRequest("123456", "asd@gmail.com");

        doThrow(new RuntimeException()).when(emailSender).send(any(SimpleMailMessage.class));

        assertThrows(EmailSendException.class, () -> emailService.sendEmail(request));
    }
}
