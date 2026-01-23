package com.aatreya.inventorymgmt.kafka.shipnode;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShipNodeConsumerDSV {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ShipNodeConsumerDSV.class);

    @KafkaListener(topics = "shipnode-DSV-topic", groupId = "shipnode-DSV-group")
    public void consumer(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }
}