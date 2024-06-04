package com.example.mfa.generator.services;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    public static final String MFA_CODE_PREFIX = "mfa:generated:";
    public static final String PROVIDED_EMAIL_PREFIX = "emails:provided:";

    private RedisTemplate<String, String> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void insertMfaCode(String code, String email) {
        redisTemplate.opsForValue().set(MFA_CODE_PREFIX + code, email, 30, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(PROVIDED_EMAIL_PREFIX + email, code, 30, TimeUnit.SECONDS);
    }

    public boolean hasCode(String code) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(MFA_CODE_PREFIX + code));
    }

    public boolean hasEmail(String email) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(PROVIDED_EMAIL_PREFIX + email));
    }
}
