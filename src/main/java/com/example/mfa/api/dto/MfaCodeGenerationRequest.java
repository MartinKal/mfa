package com.example.mfa.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a request to generate a Multi-Factor Authentication (MFA) code.
 *
 * @param email the email address for which the MFA code is to be generated.
 *              Must not be blank and must be a valid email format.
 */
public record MfaCodeGenerationRequest(@Email(message = INVALID_MFA_REQUEST)
                                       @NotBlank(message = BLANK_MFA_REQUEST) String email) {

    private static final String BLANK_MFA_REQUEST = "Please provide email";
    private static final String INVALID_MFA_REQUEST = "Provided email is invalid";
}
