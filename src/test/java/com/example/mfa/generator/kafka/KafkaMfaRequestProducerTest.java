package com.example.mfa.generator.kafka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class KafkaMfaRequestProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private KafkaMfaRequestProducer producer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMfaCodeGenerationRequest() {
        String email = "asd@gmail.com";

        producer.sendMfaCodeGenerationRequest(email);

        verify(kafkaTemplate, times(1)).send(KafkaMfaRequestProducer.TOPIC, email);
    }
}
