package com.aatreya.inventorymgmt.kafka;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest(classes = {KafkaProducer.class, KafkaProducerTests.TestConfig.class})
class KafkaProducerTests {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @TestConfiguration
    static class TestConfig {
        @Bean
        KafkaTemplate<String, String> kafkaTemplate() {
            @SuppressWarnings("unchecked")
            KafkaTemplate<String, String> template = (KafkaTemplate<String, String>) Mockito.mock(KafkaTemplate.class);
            return template;
        }
    }

    @Test
    void sendMessage_validTopicAndMessage_sendsToKafkaTemplate() {
        kafkaProducer.sendMessage("topic", "message");
        verify(kafkaTemplate).send("topic", "message");
    }

    @Test
    void sendMessage_nullTopic_throwsException() {
        assertThatThrownBy(() -> kafkaProducer.sendMessage(null, "message"))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("topic must not be null");
    }

    @Test
    void sendMessage_nullMessage_throwsException() {
        assertThatThrownBy(() -> kafkaProducer.sendMessage("topic", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("message must not be null");
    }
}