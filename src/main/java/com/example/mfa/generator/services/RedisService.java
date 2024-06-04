package com.example.mfa.generator.services;

/**
 * Service for interacting with Redis
 */
public interface RedisService {

    /**
     * Inserts an MFA code and associated email into our Redis cache.
     * @param code  the MFA code to be stored
     * @param email the email address associated with the MFA code
     */
    void insertMfaCode(String code, String email);

    /**
     * Checks if the specified MFA code exists in our Redis cache.
     * @param code the MFA code to check
     * @return true if the MFA code exists in the cache, false otherwise
     */
    boolean hasCode(String code);

    /**
     * Checks if the specified email address exists in our Redis cache.
     * @param email the email address to check
     * @return true if the email address exists in the cache, false otherwise
     */
    boolean hasEmail(String email);
}
