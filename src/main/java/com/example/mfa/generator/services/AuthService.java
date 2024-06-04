package com.example.mfa.generator.services;

import com.example.mfa.api.dto.MfaCodeGenerationRequest;

public interface AuthService {

    void issueMfaCodeAsync(MfaCodeGenerationRequest request);
}
