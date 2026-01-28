package com.aatreya.inventorymgmt.kafka.shipnode;

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

@SpringBootTest(classes = {ShipNodeProducer.class, ShipNodeProducerTests.TestConfig.class})
class ShipNodeProducerTests {

    @Autowired
    private ShipNodeProducer shipNodeProducer;

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
        shipNodeProducer.sendMessage("shipnode-first-second-party-topic", "payload");
        verify(kafkaProducer).sendMessage("shipnode-first-second-party-topic", "payload");
    }

    @Test
    void sendMessage_nullTopic_doesNotSend() {
        shipNodeProducer.sendMessage(null, "payload");
        verifyNoInteractions(kafkaProducer);
    }

    @Test
    void sendMessage_emptyTopic_doesNotSend() {
        shipNodeProducer.sendMessage("", "payload");
        verifyNoInteractions(kafkaProducer);
    }

    @Test
    void sendMessage_unknownTopic_doesNotSend() {
        shipNodeProducer.sendMessage("unknown-topic", "payload");
        verifyNoInteractions(kafkaProducer);
    }
}