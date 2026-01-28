package com.aatreya.inventorymgmt.kafka.inventory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.aatreya.inventorymgmt.model.Inventory;
import com.aatreya.inventorymgmt.repository.InventoryRepository;

@Service
@ConditionalOnProperty(prefix = "spring.cloud.azure.cosmos", name = "enabled", havingValue = "true")
public class InventoryConsumerDSV {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(InventoryConsumerDSV.class);
    private final InventoryRepository inventoryRepository;

    public InventoryConsumerDSV(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @KafkaListener(topics = "inventory-DSV-topic", groupId = "inventory-DSV-group", containerFactory = "inventoryKafkaListenerContainerFactory")
    public void consumer(Inventory inventory) {
        try {
            LOGGER.info(String.format("Message received -> %s", inventory.toString()));
            
            // Validate required fields
            if (inventory.getShipNode() == null || inventory.getShipNode().isEmpty()) {
                LOGGER.error("ShipNode (partition key) cannot be null or empty");
                return;
            }
            
            inventoryRepository.save(inventory);
            LOGGER.info(String.format("Inventory saved to Cosmos DB with ID: %s", inventory.getId()));
        } catch (Exception e) {
            LOGGER.error(String.format("Error saving inventory to database: %s", e.getMessage()), e);
        }
    }
}