package com.aatreya.inventorymgmt.kafka.inventory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.aatreya.inventorymgmt.model.Inventory;

@Service
public class InventoryConsumerStore {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(InventoryConsumerStore.class);

    @KafkaListener(topics = "inventory-store-topic", groupId = "inventory-store-group")
    public void consumer(Inventory inventory) {
        LOGGER.info(String.format("Message received -> %s", inventory.toString()));
    }
}