package com.example.mfa.api;

import com.example.mfa.api.dto.MfaCodeGenerationRequest;
import com.example.mfa.api.dto.MfaCodeValidationRequest;
import com.example.mfa.generator.services.AuthService;
import com.example.mfa.generator.services.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MfaCodeGenerationControllerTest {

    private static final String URL_PREFIX = "/api/v1/mfa/";
    @Mock
    private AuthService authService;

    @Mock
    private RedisService redisService;

    @InjectMocks
    private MfaCodeGenerationController codeGenerationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(codeGenerationController).build();
    }

    @Test
    public void testGenerateMfaCode() throws Exception {
        MfaCodeGenerationRequest request = new MfaCodeGenerationRequest("asd@gmail.com");

        mockMvc.perform(post(URL_PREFIX + "generation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"asd@gmail.com\"}"))
                .andExpect(status().isOk());

        verify(authService, times(1)).issueMfaCodeAsync(request);
    }

    @Test
    public void testValidateMfaCode() throws Exception {
        MfaCodeValidationRequest request = new MfaCodeValidationRequest("123456");

        when(redisService.hasCode("123456")).thenReturn(true);

        mockMvc.perform(post(URL_PREFIX + "validation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\":\"123456\"}"))
                .andExpect(status().isOk());

        verify(redisService, times(1)).hasCode("123456");
    }
}
