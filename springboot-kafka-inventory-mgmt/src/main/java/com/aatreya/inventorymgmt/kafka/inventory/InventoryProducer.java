package com.aatreya.inventorymgmt.kafka.inventory;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.aatreya.inventorymgmt.kafka.KafkaProducer;

@Service
public class InventoryProducer {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(InventoryProducer.class);

    KafkaProducer kafkaProducer;

    public InventoryProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    public void sendMessage(String topic, String message) {
        if (topic == null || topic.isEmpty()) {
            LOGGER.warn("Attempted to send message with null or empty topic");
        }
        else if (topic.equals("inventory-first-second-party-topic") ||
                 topic.equals("inventory-DSV-topic") ||
                 topic.equals("inventory-marketplace-topic") ||
                 topic.equals("inventory-store-topic")) {
            LOGGER.info(String.format("Message sent -> %s", message));
            kafkaProducer.sendMessage(topic, message);
        } else {
            LOGGER.warn(String.format("Sending message to unknown topic: %s", topic));
        }
        
    }
}
