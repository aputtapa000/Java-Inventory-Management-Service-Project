package com.aatreya.inventorymgmt.kafka.shipnode;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShipNodeConsumerFirstSecondParty {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ShipNodeConsumerFirstSecondParty.class);

    @KafkaListener(topics = "shipnode-first-second-party-topic", groupId = "shipnode-first-second-party-group")
    public void consumer(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }
}
