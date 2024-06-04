package com.example.mfa.api.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record MfaCodeValidationRequest(@NotBlank @Length(min = 4, max = 6) String code) {
}
