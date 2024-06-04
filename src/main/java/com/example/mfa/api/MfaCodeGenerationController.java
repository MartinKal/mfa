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

    @PostMapping("/generation")
    public ResponseEntity<String> generateMfaCode(@RequestBody @Valid MfaCodeGenerationRequest request) {
        authService.issueMfaCodeAsync(request);

        return ResponseEntity.ok(RESPONSE_MESSAGE);
    }

    @PostMapping("/validation")
    public ResponseEntity<Boolean> validateMfaCode(@RequestBody @Valid MfaCodeValidationRequest request) {
        return ResponseEntity.ok(redisService.hasCode(request.code()));
    }
}
