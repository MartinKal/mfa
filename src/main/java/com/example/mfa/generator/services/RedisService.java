package com.example.mfa.generator.services;

public interface RedisService {

    void insertMfaCode(String code, String email);

    boolean hasCode(String code);

    boolean hasEmail(String email);
}
