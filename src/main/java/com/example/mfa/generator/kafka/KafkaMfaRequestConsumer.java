package com.example.mfa.generator.kafka;

import com.example.mfa.email.dto.EmailRequest;
import com.example.mfa.email.services.EmailService;
import com.example.mfa.generator.services.MfaCodeGeneratorService;
import com.example.mfa.generator.services.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMfaRequestConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMfaRequestConsumer.class);
    private MfaCodeGeneratorService codeGeneratorService;
    private RedisService redisService;
    private EmailService emailService;

    public KafkaMfaRequestConsumer(
            MfaCodeGeneratorService codeGeneratorService,
            RedisService redisService,
            EmailService emailService) {
        this.codeGeneratorService = codeGeneratorService;
        this.redisService = redisService;
        this.emailService = emailService;
    }

    @KafkaListener(topics = KafkaMfaRequestProducer.TOPIC, groupId = "group-generate-mfa")
    public void consume(String email) {
        logger.info("Received mfa code generation request for email: {}", email);

        if (redisService.hasEmail(email)) {
            return;
        }

        String code = codeGeneratorService.generateMfaCode();
        redisService.insertMfaCode(code, email);
        emailService.sendEmail(new EmailRequest(code, email));
    }
}
