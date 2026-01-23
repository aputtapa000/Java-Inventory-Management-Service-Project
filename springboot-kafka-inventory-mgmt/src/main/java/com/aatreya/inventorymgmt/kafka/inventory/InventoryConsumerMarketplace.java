package com.aatreya.inventorymgmt.kafka.inventory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumerMarketplace {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(InventoryConsumerMarketplace.class);

    @KafkaListener(topics = "inventory-marketplace-topic", groupId = "inventory-marketplace-group")
    public void consumer(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }
}