package com.example.mfa.api;

import com.example.mfa.api.dto.MfaCodeGenerationRequest;
import com.example.mfa.api.dto.MfaCodeValidationRequest;
import com.example.mfa.generator.services.AuthService;
import com.example.mfa.generator.services.RedisService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling Multi-Factor Authentication (MFA) code generation and validation.
 */
@RestController
@RequestMapping("api/v1/mfa")
@Validated
public class MfaCodeGenerationController {
    private static final String RESPONSE_MESSAGE = "Mfa request received. Code is prepared and will be sent shortly.";

    private AuthService authService;
    private RedisService redisService;

    public MfaCodeGenerationController(AuthService authService, RedisService redisService) {
        this.authService = authService;
        this.redisService = redisService;
    }

    /**
     * Endpoint for generating an MFA code.
     * This endpoint accepts a POST request, validates it, and triggers asynchronous generation of the MFA code.
     * If successful, the code is sent to the provided email address and kept in a redis cache for 30 seconds.
     *
     * @param request the MFA code generation request payload
     * @return a response entity with a message indicating that the MFA request has been received
     */
    @PostMapping("/generation")
    public ResponseEntity<String> generateMfaCode(@RequestBody @Valid MfaCodeGenerationRequest request) {
        authService.issueMfaCodeAsync(request);

        return ResponseEntity.ok(RESPONSE_MESSAGE);
    }

    /**
     * Endpoint for validating an MFA code.
     * This endpoint accepts a POST request validates it, and checks the code against stored values in Redis cache.
     *
     * @param request the MFA code validation request payload
     * @return a response entity with a boolean indicating whether the MFA code is still valid (that is to be still
     * present in the redis cache)
     */
    @PostMapping("/validation")
    public ResponseEntity<Boolean> validateMfaCode(@RequestBody @Valid MfaCodeValidationRequest request) {
        return ResponseEntity.ok(redisService.hasCode(request.code()));
    }
}
