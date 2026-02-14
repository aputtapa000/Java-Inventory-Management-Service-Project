package com.aatreya.inventorymgmt.kafka.shipnode;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.aatreya.inventorymgmt.model.ShipNode;

@Service
public class ShipNodeProducer {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ShipNodeProducer.class);

    private KafkaTemplate<String, ShipNode> kafkaTemplate;

    public ShipNodeProducer(@Qualifier("shipNodeKafkaTemplate") KafkaTemplate<String, ShipNode> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @SuppressWarnings("null")
    public void sendMessage(String topic, ShipNode shipNode) {
        

        if (topic == null || topic.isEmpty()) {
            LOGGER.warn("Attempted to send message with null or empty topic");
        }
        else if (topic.equals("inventory-first-second-party-topic") ||
                 topic.equals("inventory-DSV-topic") ||
                 topic.equals("inventory-marketplace-topic") ||
                 topic.equals("inventory-store-topic")) {
            LOGGER.info(String.format("Message sent -> %s", shipNode.toString()));
            Message<ShipNode> message = MessageBuilder
            .withPayload(shipNode)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .build();
            kafkaTemplate.send(message);
        } 
        else {
            LOGGER.warn(String.format("Sending message to unknown topic: %s", topic));
        }
    }

}