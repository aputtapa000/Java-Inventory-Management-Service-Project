/* 
package com.aatreya.inventorymgmt.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.aatreya.inventorymgmt.payload.User;

@Service
public class JsonKafkaConsumer {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "my-topic-json", groupId = "my-group")
    public void consume(User user) {
        LOGGER.info(String.format("Json Message received -> %s", user.toString()));
    }
}
 */