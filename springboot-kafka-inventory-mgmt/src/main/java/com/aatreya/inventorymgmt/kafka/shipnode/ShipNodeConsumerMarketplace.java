package com.aatreya.inventorymgmt.kafka.shipnode;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShipNodeConsumerMarketplace {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ShipNodeConsumerMarketplace.class);

    @KafkaListener(topics = "shipnode-marketplace-topic", groupId = "shipnode-marketplace-group")
    public void consumer(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }
}