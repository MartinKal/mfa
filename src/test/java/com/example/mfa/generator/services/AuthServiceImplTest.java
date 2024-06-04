package com.example.mfa.generator.services;

import com.example.mfa.api.dto.MfaCodeGenerationRequest;
import com.example.mfa.generator.kafka.KafkaMfaRequestProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AuthServiceImplTest {

    @Mock
    private KafkaMfaRequestProducer kafkaMfaRequestProducer;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIssueMfaCodeAsync() {
        String email = "asd@abv.com";
        MfaCodeGenerationRequest request = new MfaCodeGenerationRequest(email);

        authService.issueMfaCodeAsync(request);

        verify(kafkaMfaRequestProducer, times(1)).sendMfaCodeGenerationRequest(email);
    }
}
