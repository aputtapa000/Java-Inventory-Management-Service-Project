package com.aatreya.inventorymgmt.kafka.inventory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.aatreya.inventorymgmt.repository.InventoryRepository;
import com.aatreya.inventorymgmt.model.Inventory;

@Service
public class InventoryConsumerFirstSecondParty {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(InventoryConsumerFirstSecondParty.class);
    private final InventoryRepository inventoryRepository;

    public InventoryConsumerFirstSecondParty(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @KafkaListener(topics = "inventory-first-second-party-topic", groupId = "inventory-first-second-party-group")
    public void consumer(Inventory inventory) {
        LOGGER.info(String.format("Message received -> %s", inventory.toString()));
        inventoryRepository.save(inventory);
    }
}
