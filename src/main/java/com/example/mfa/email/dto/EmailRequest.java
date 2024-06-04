package com.example.mfa.email.dto;

/**
 * Represents a request containing an email address and an associated generated mfa code.
 */
public record EmailRequest(String code, String email) {
}
