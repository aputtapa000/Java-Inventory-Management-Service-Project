package com.aatreya.inventorymgmt.kafka.inventory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumerFirstParty {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(InventoryConsumerFirstParty.class);

    @KafkaListener(topics = "inventory-first-party-topic", groupId = "inventory-first-party-group")
    public void consumer(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }
}
