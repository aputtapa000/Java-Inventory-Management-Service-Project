package com.aatreya.inventorymgmt.kafka.item;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verify;

import com.aatreya.inventorymgmt.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.mockito.ArgumentCaptor;

@SpringBootTest(classes = {ItemProducer.class, ItemProducerTests.TestConfig.class})
class ItemProducerTests {

    @Autowired
    private ItemProducer itemProducer;

    @Autowired
    private KafkaTemplate<String, Item> itemKafkaTemplate;

    @TestConfiguration
    static class TestConfig {
        @Bean
        KafkaTemplate<String, Item> itemKafkaTemplate() {
            @SuppressWarnings("unchecked")
            KafkaTemplate<String, Item> template =
                    (KafkaTemplate<String, Item>) Mockito.mock(KafkaTemplate.class);
            return template;
        }
    }

    @BeforeEach
    void resetMocks() {
        Mockito.reset((Object) itemKafkaTemplate);
    }

    @Test
    @SuppressWarnings("null")
    void sendMessage_validTopic_sendsMessageWithTopicHeader() {
        Item item = new Item();

        itemProducer.sendMessage("inventory-store-topic", item);

        ArgumentCaptor<Message<?>> captor = ArgumentCaptor.forClass(Message.class);
        verify(itemKafkaTemplate).send(captor.capture());
        @SuppressWarnings("unchecked")
        Message<Item> message = (Message<Item>) captor.getValue();
        assertThat(message.getPayload()).isSameAs(item);
        assertThat(message.getHeaders().get(KafkaHeaders.TOPIC)).isEqualTo("inventory-store-topic");
    }

    @Test
    void sendMessage_nullTopic_doesNotSend() {
        itemProducer.sendMessage(null, new Item());
        verifyNoInteractions(itemKafkaTemplate);
    }

    @Test
    void sendMessage_emptyTopic_doesNotSend() {
        itemProducer.sendMessage("", new Item());
        verifyNoInteractions(itemKafkaTemplate);
    }

    @Test
    void sendMessage_unknownTopic_doesNotSend() {
        itemProducer.sendMessage("unknown-topic", new Item());
        verifyNoInteractions(itemKafkaTemplate);
    }
}