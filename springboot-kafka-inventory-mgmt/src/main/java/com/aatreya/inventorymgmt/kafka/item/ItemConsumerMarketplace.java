package com.aatreya.inventorymgmt.kafka.item;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ItemConsumerMarketplace {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ItemConsumerMarketplace.class);

    @KafkaListener(topics = "item-marketplace-topic", groupId = "item-marketplace-group")
    public void consumer(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }
}