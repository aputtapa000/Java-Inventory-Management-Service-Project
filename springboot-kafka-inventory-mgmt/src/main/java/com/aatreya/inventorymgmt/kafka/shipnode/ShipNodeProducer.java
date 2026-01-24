package com.aatreya.inventorymgmt.kafka.shipnode;

import org.slf4j.Logger;

import org.springframework.stereotype.Service;

import com.aatreya.inventorymgmt.kafka.KafkaProducer;

@Service
public class ShipNodeProducer {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ShipNodeProducer.class);

    KafkaProducer kafkaProducer;

    public ShipNodeProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    
    public void sendMessage(String topic, String message) {
        if (topic == null || topic.isEmpty()) {
            LOGGER.warn("Attempted to send message with null or empty topic");
        }
        else if (topic.equals("shipnode-first-second-party-topic") ||
                 topic.equals("shipnode-DSV-topic") ||
                 topic.equals("shipnode-marketplace-topic")) {
            LOGGER.info(String.format("Message sent -> %s", message));
            kafkaProducer.sendMessage(topic, message);
        } else {
            LOGGER.warn(String.format("Sending message to unknown topic: %s", topic));
        }
        
    }
}
