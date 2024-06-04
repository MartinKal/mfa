package com.example.mfa.generator.services;

import com.example.mfa.api.dto.MfaCodeGenerationRequest;

/**
 * Service for handling authentication-related operations.
 */
public interface AuthService {

    /**
     * Starts the process of issuing an MFA code and later sending it to the email address of the recipient.
     * The operation is performed asynchronously to avoid blocking the calling thread.
     * @param request the MFA code generation request containing the details needed to generate MFA code and send it to
     *                recipient's email
     */
    void issueMfaCodeAsync(MfaCodeGenerationRequest request);
}
