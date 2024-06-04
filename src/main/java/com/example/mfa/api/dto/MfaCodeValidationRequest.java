package com.example.mfa.api.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * Represents a request to validate a Multi-Factor Authentication (MFA) code.
 *
 * @param code the MFA code to be validated.
 *             Must not be blank and must be exactly 6 characters long.
 */
public record MfaCodeValidationRequest(@NotBlank @Length(min = 6, max = 6) String code) {
}
