package com.example.mfa.generator.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Service for producing Kafka messages related to MFA code generation requests.
 */
@Service
public class KafkaMfaRequestProducer {
    public static final String TOPIC = "generate-mfa-code";
    private static final Logger logger = LoggerFactory.getLogger(KafkaMfaRequestProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMfaRequestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends an MFA code generation request to the Kafka topic.
     * @param email the email address for which to generate an MFA code
     */
    public void sendMfaCodeGenerationRequest(String email) {
        logger.info("Received mfa code generation request for email: {}", email);
        kafkaTemplate.send(TOPIC, email);
    }
}
