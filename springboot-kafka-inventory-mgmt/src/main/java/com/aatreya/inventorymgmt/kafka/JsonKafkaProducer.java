/*
package com.aatreya.inventorymgmt.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.aatreya.inventorymgmt.model.Inventory;

@Service
public class JsonKafkaProducer {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, Inventory> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, Inventory> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Inventory inventory) {
        

        if (topic == null || topic.isEmpty()) {
            LOGGER.warn("Attempted to send message with null or empty topic");
        }
        else if (topic.equals("inventory-first-second-party-topic") ||
                 topic.equals("inventory-DSV-topic") ||
                 topic.equals("inventory-marketplace-topic") ||
                 topic.equals("inventory-store-topic")) {
            LOGGER.info(String.format("Message sent -> %s", inventory.toString()));
            Message<Inventory> message = MessageBuilder
            .withPayload(inventory)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .build();
            kafkaTemplate.send(message);
        } 
        else {
            LOGGER.warn(String.format("Sending message to unknown topic: %s", topic));
        }
    }

}
*/