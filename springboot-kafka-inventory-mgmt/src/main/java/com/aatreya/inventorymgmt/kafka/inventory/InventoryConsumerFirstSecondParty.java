package com.aatreya.inventorymgmt.kafka.inventory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumerFirstSecondParty {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(InventoryConsumerFirstSecondParty.class);

    @KafkaListener(topics = "inventory-first-second-party-topic", groupId = "inventory-first-second-party-group")
    public void consumer(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }
}
