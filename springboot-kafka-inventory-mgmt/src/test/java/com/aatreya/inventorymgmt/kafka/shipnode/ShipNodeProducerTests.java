package com.aatreya.inventorymgmt.kafka.shipnode;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

import com.aatreya.inventorymgmt.model.ShipNode;
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

@SpringBootTest(classes = {ShipNodeProducer.class, ShipNodeProducerTests.TestConfig.class})
class ShipNodeProducerTests {

    @Autowired
    private ShipNodeProducer shipNodeProducer;

    @Autowired
    private KafkaTemplate<String, ShipNode> shipNodeKafkaTemplate;

    @TestConfiguration
    static class TestConfig {
        @Bean
        KafkaTemplate<String, ShipNode> shipNodeKafkaTemplate() {
            @SuppressWarnings("unchecked")
            KafkaTemplate<String, ShipNode> template =
                    (KafkaTemplate<String, ShipNode>) Mockito.mock(KafkaTemplate.class);
            return template;
        }
    }

    @BeforeEach
    void resetMocks() {
        Mockito.reset((Object) shipNodeKafkaTemplate);
    }

    @Test
    @SuppressWarnings("null")
    void sendMessage_validTopic_sendsMessageWithTopicHeader() {
        ShipNode shipNode = new ShipNode();

        shipNodeProducer.sendMessage("inventory-store-topic", shipNode);

        ArgumentCaptor<Message<?>> captor = ArgumentCaptor.forClass(Message.class);
        verify(shipNodeKafkaTemplate).send(captor.capture());
        @SuppressWarnings("unchecked")
        Message<ShipNode> message = (Message<ShipNode>) captor.getValue();
        assertThat(message.getPayload()).isSameAs(shipNode);
        assertThat(message.getHeaders().get(KafkaHeaders.TOPIC)).isEqualTo("inventory-store-topic");
    }

    @Test
    void sendMessage_nullTopic_doesNotSend() {
        shipNodeProducer.sendMessage(null, new ShipNode());
        verifyNoInteractions(shipNodeKafkaTemplate);
    }

    @Test
    void sendMessage_emptyTopic_doesNotSend() {
        shipNodeProducer.sendMessage("", new ShipNode());
        verifyNoInteractions(shipNodeKafkaTemplate);
    }

    @Test
    void sendMessage_unknownTopic_doesNotSend() {
        shipNodeProducer.sendMessage("unknown-topic", new ShipNode());
        verifyNoInteractions(shipNodeKafkaTemplate);
    }
}