package com.example.mfa.generator.kafka;

import com.example.mfa.email.dto.EmailRequest;
import com.example.mfa.email.services.EmailService;
import com.example.mfa.generator.services.MfaCodeGeneratorService;
import com.example.mfa.generator.services.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class KafkaMfaRequestConsumerTest {
    @Mock
    private MfaCodeGeneratorService codeGeneratorService;

    @Mock
    private RedisService redisService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private KafkaMfaRequestConsumer consumer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsume() {
        String email = "asd@gmail.com";
        String code = "123456";

        when(redisService.hasEmail(email)).thenReturn(false);
        when(codeGeneratorService.generateMfaCode()).thenReturn(code);

        consumer.consume(email);

        verify(redisService, times(1)).insertMfaCode(code, email);
        verify(emailService, times(1)).sendEmail(new EmailRequest(code, email));
    }
}
