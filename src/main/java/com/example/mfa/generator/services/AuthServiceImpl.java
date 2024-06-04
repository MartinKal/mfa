package com.example.mfa.generator.services;

import com.example.mfa.api.dto.MfaCodeGenerationRequest;
import com.example.mfa.generator.kafka.KafkaMfaRequestProducer;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private KafkaMfaRequestProducer kafkaMfaRequestProducer;

    public AuthServiceImpl(KafkaMfaRequestProducer kafkaMfaRequestProducer) {
        this.kafkaMfaRequestProducer = kafkaMfaRequestProducer;
    }

    @Override
    public void issueMfaCodeAsync(MfaCodeGenerationRequest request) {
        kafkaMfaRequestProducer.sendMfaCodeGenerationRequest(request.email());
    }
}
