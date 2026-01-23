package com.aatreya.inventorymgmt.kafka.item;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ItemConsumerDSV {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ItemConsumerDSV.class);

    @KafkaListener(topics = "item-DSV-topic", groupId = "item-DSV-group")
    public void consumer(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }
}