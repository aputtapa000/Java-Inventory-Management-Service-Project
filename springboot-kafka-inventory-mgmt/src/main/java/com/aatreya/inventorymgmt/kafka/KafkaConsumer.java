/* package com.aatreya.inventorymgmt.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void consumer(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }
}

 */