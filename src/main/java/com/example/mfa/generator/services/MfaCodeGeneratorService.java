package com.example.mfa.generator.services;

/**
 * Service for generating Multi-Factor Authentication (MFA) codes.
 */
public interface MfaCodeGeneratorService {

    /**
     * Generates a new MFA code.
     * This method generates a secure and unique MFA code. The exact format and security measures
     * are determined by the implementing class.
     * @return a newly generated MFA code
     */
    String generateMfaCode();
}
