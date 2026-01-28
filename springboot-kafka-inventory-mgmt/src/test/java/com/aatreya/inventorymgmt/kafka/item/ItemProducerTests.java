package com.aatreya.inventorymgmt.kafka.item;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.aatreya.inventorymgmt.kafka.KafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest(classes = {ItemProducer.class, ItemProducerTests.TestConfig.class})
class ItemProducerTests {

    @Autowired
    private ItemProducer itemProducer;

    @Autowired
    private KafkaProducer kafkaProducer;

    @TestConfiguration
    static class TestConfig {
        @Bean
        KafkaProducer kafkaProducer() {
            return Mockito.mock(KafkaProducer.class);
        }
    }

    @BeforeEach
    void resetMocks() {
        Mockito.reset(kafkaProducer);
    }

    @Test
    void sendMessage_validTopic_delegatesToKafkaProducer() {
        itemProducer.sendMessage("item-first-second-party-topic", "payload");
        verify(kafkaProducer).sendMessage("item-first-second-party-topic", "payload");
    }

    @Test
    void sendMessage_nullTopic_doesNotSend() {
        itemProducer.sendMessage(null, "payload");
        verifyNoInteractions(kafkaProducer);
    }

    @Test
    void sendMessage_emptyTopic_doesNotSend() {
        itemProducer.sendMessage("", "payload");
        verifyNoInteractions(kafkaProducer);
    }

    @Test
    void sendMessage_unknownTopic_doesNotSend() {
        itemProducer.sendMessage("unknown-topic", "payload");
        verifyNoInteractions(kafkaProducer);
    }
}