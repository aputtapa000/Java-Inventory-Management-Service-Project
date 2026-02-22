package com.aatreya.inventorymgmt.kafka.inventory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.aatreya.inventorymgmt.repository.InventoryRepository;
import com.aatreya.inventorymgmt.model.Inventory;

@Service
@ConditionalOnProperty(prefix = "spring.cloud.azure.cosmos", name = "enabled", havingValue = "true")
public class InventoryConsumerFirstSecondParty {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(InventoryConsumerFirstSecondParty.class);
    private final InventoryRepository inventoryRepository;

    public InventoryConsumerFirstSecondParty(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @KafkaListener(topics = "inventory-first-second-party-topic", groupId = "inventory-first-second-party-group", containerFactory = "inventoryKafkaListenerContainerFactory")
    public void consumer(Inventory inventory) {
        try {
            LOGGER.info(String.format("Message received -> %s", inventory.toString()));
            
            // Validate required fields
            if (inventory.getShipNode() == null ){
                LOGGER.error("ShipNode (partition key) cannot be null or empty");
                return;
            }
            if (inventory.getItem_sku() <= 0) {
                LOGGER.error("Item SKU must be a positive number");
                return;
            }
            if (inventory.getQuantityUpdate() <= 0) {
                LOGGER.error("Quantity update must be a positive number");
                return;
            }
            
            inventoryRepository.save(inventory);
            LOGGER.info(String.format("Inventory saved to Cosmos DB with ID: %s", inventory.getUpdateId()));
        } catch (Exception e) {
            LOGGER.error(String.format("Error saving inventory to database: %s", e.getMessage()), e);
        }
    }
}
