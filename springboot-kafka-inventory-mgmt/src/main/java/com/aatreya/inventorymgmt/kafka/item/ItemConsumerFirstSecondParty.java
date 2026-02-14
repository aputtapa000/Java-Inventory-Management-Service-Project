package com.aatreya.inventorymgmt.kafka.item;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.aatreya.inventorymgmt.repository.ItemRepository;
import com.aatreya.inventorymgmt.model.Item;

@Service
@ConditionalOnProperty(prefix = "spring.cloud.azure.cosmos", name = "enabled", havingValue = "true")
public class ItemConsumerFirstSecondParty {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ItemConsumerFirstSecondParty.class);
    private final ItemRepository itemRepository;

    public ItemConsumerFirstSecondParty(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @KafkaListener(topics = "item-first-second-party-topic", groupId = "item-first-second-party-group", containerFactory = "itemKafkaListenerContainerFactory")
    public void consumer(Item item) {
        try{
        LOGGER.info(String.format("Message received -> %s", item.toString()));

            // Validate required fields
            if (item.getSku_id() == 0) {
                LOGGER.error("SKU ID cannot be null or empty");
                return;
            }
            if (item.getCategory() == null || item.getCategory().isEmpty()) {
                LOGGER.error("Category (partition key) cannot be null or empty");
                return;
            }
            itemRepository.save(item);
            LOGGER.info(String.format("Item saved to Cosmos DB with SKU ID: %s", item.getSku_id()));

        } catch (Exception e) {
            LOGGER.error(String.format("Error processing item message: %s", e.getMessage()), e);
        }
    }
}