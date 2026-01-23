/* package com.aatreya.inventorymgmt.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.aatreya.inventorymgmt.payload.User;

@Service
public class JsonKafkaProducer {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, User> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user) {
        LOGGER.info(String.format("Message sent -> %s", user.toString()));

        Message<User> message = MessageBuilder
            .withPayload(user)
            .setHeader(KafkaHeaders.TOPIC, "my-topic-json")
            .build();
        
        kafkaTemplate.send(message);
    }

}
 */