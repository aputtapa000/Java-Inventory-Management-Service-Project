package com.aatreya.inventorymgmt.kafka;

import jakarta.annotation.Nonnull;
import java.util.Objects;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(@Nonnull String topic, @Nonnull String message) {
        Objects.requireNonNull(topic, "topic must not be null");
        Objects.requireNonNull(message, "message must not be null");
        LOGGER.info(String.format("Message sent -> %s", message));
        kafkaTemplate.send(topic, message);
    }
}
