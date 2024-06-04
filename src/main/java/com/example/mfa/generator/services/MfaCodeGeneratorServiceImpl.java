package com.example.mfa.generator.services;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.springframework.stereotype.Service;

@Service
public class MfaCodeGeneratorServiceImpl implements MfaCodeGeneratorService {

    private GoogleAuthenticator googleAuthenticator;

    public MfaCodeGeneratorServiceImpl() {
        googleAuthenticator = new GoogleAuthenticator();
    }

    public String generateMfaCode() {
        String secret = generateSecretKeyForEmail();
        int code = googleAuthenticator.getTotpPassword(secret);
        return String.valueOf(code);
    }

    private String generateSecretKeyForEmail() {
        return googleAuthenticator.createCredentials().getKey();
    }
}
