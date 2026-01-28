package com.aatreya.inventorymgmt.kafka.inventory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.aatreya.inventorymgmt.model.Inventory;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

@SpringBootTest(classes = {InventoryProducer.class, InventoryProducerTests.TestConfig.class})
class InventoryProducerTests {

    @Autowired
    private InventoryProducer inventoryProducer;

    @Autowired
    private KafkaTemplate<String, Inventory> inventoryKafkaTemplate;

    @TestConfiguration
    static class TestConfig {
        @Bean(name = "inventoryKafkaTemplate")
        KafkaTemplate<String, Inventory> inventoryKafkaTemplate() {
            @SuppressWarnings("unchecked")
            KafkaTemplate<String, Inventory> template =
                    (KafkaTemplate<String, Inventory>) Mockito.mock(KafkaTemplate.class);
            return template;
        }
    }

    @Test
    @SuppressWarnings("null")
    void sendMessage_validTopic_sendsMessageWithTopicHeader() {
        Inventory inventory = new Inventory("item", 1, "loc", "sn-1");

        inventoryProducer.sendMessage("inventory-store-topic", inventory);

        ArgumentCaptor<Message<?>> captor = ArgumentCaptor.forClass(Message.class);
        verify(inventoryKafkaTemplate).send(captor.capture());
        @SuppressWarnings("unchecked")
        Message<Inventory> message = (Message<Inventory>) captor.getValue();
        assertThat(message.getPayload()).isSameAs(inventory);
        assertThat(message.getHeaders().get(KafkaHeaders.TOPIC)).isEqualTo("inventory-store-topic");
    }

    @Test
    void sendMessage_nullTopic_doesNotSend() {
        Inventory inventory = new Inventory("item", 1, "loc", "sn-1");
        inventoryProducer.sendMessage(null, inventory);
        verifyNoInteractions(inventoryKafkaTemplate);
    }

    @Test
    void sendMessage_emptyTopic_doesNotSend() {
        Inventory inventory = new Inventory("item", 1, "loc", "sn-1");
        inventoryProducer.sendMessage("", inventory);
        verifyNoInteractions(inventoryKafkaTemplate);
    }

    @Test
    void sendMessage_unknownTopic_doesNotSend() {
        Inventory inventory = new Inventory("item", 1, "loc", "sn-1");
        inventoryProducer.sendMessage("unknown-topic", inventory);
        verifyNoInteractions(inventoryKafkaTemplate);
    }
}