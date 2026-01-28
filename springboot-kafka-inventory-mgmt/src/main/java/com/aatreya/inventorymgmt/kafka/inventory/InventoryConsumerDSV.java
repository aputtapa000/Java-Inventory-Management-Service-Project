package com.aatreya.inventorymgmt.kafka.inventory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.aatreya.inventorymgmt.model.Inventory;

@Service
public class InventoryConsumerDSV {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(InventoryConsumerDSV.class);

    @KafkaListener(topics = "inventory-DSV-topic", groupId = "inventory-DSV-group")
    public void consumer(Inventory inventory) {
        LOGGER.info(String.format("Message received -> %s", inventory.toString()));
    }
}