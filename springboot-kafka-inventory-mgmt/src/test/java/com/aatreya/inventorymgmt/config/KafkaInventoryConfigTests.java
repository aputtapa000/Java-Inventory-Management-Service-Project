package com.aatreya.inventorymgmt.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.aatreya.inventorymgmt.model.Inventory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@SpringBootTest(
        classes = KafkaInventoryConfig.class,
        properties = {
                "spring.kafka.bootstrap-servers=localhost:9092",
                "spring.kafka.consumer.auto-offset-reset=earliest"
        })
class KafkaInventoryConfigTests {

    @Autowired
    @Qualifier("inventoryProducerFactory")
    private ProducerFactory<String, Inventory> inventoryProducerFactory;

    @Autowired
    @Qualifier("stringProducerFactory")
    private ProducerFactory<String, String> stringProducerFactory;

    @Autowired
    @Qualifier("inventoryKafkaTemplate")
    private KafkaTemplate<String, Inventory> inventoryKafkaTemplate;

    @Autowired
    @Qualifier("kafkaTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    @Qualifier("inventoryConsumerFactory")
    private ConsumerFactory<String, Inventory> inventoryConsumerFactory;

    @Autowired
    @Qualifier("stringConsumerFactory")
    private ConsumerFactory<String, String> stringConsumerFactory;

    @Autowired
    @Qualifier("inventoryKafkaListenerContainerFactory")
    private ConcurrentKafkaListenerContainerFactory<String, Inventory> inventoryKafkaListenerContainerFactory;

    @Autowired
    @Qualifier("kafkaListenerContainerFactory")
    private ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory;

    @Test
    void producerFactories_areCreated() {
        assertThat(inventoryProducerFactory).isNotNull();
        assertThat(stringProducerFactory).isNotNull();
    }

    @Test
    void kafkaTemplates_areCreated() {
        assertThat(inventoryKafkaTemplate).isNotNull();
        assertThat(kafkaTemplate).isNotNull();
    }

    @Test
    void consumerFactories_areCreated() {
        assertThat(inventoryConsumerFactory).isNotNull();
        assertThat(stringConsumerFactory).isNotNull();
    }

    @Test
    void listenerContainerFactories_areCreated() {
        assertThat(inventoryKafkaListenerContainerFactory).isNotNull();
        assertThat(kafkaListenerContainerFactory).isNotNull();
    }
}