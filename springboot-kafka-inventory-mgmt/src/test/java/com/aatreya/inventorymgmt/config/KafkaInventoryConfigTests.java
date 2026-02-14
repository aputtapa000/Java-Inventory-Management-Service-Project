package com.aatreya.inventorymgmt.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.aatreya.inventorymgmt.model.Inventory;
import com.aatreya.inventorymgmt.model.Item;
import com.aatreya.inventorymgmt.model.ShipNode;
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
    @Qualifier("itemProducerFactory")
    private ProducerFactory<String, Item> itemProducerFactory;

    @Autowired
    @Qualifier("shipNodeProducerFactory")
    private ProducerFactory<String, ShipNode> shipNodeProducerFactory;

    @Autowired
    @Qualifier("inventoryKafkaTemplate")
    private KafkaTemplate<String, Inventory> inventoryKafkaTemplate;

    @Autowired
    @Qualifier("itemKafkaTemplate")
    private KafkaTemplate<String, Item> itemKafkaTemplate;

    @Autowired
    @Qualifier("shipNodeKafkaTemplate")
    private KafkaTemplate<String, ShipNode> shipNodeKafkaTemplate;

    @Autowired
    @Qualifier("inventoryConsumerFactory")
    private ConsumerFactory<String, Inventory> inventoryConsumerFactory;

    @Autowired
    @Qualifier("itemConsumerFactory")
    private ConsumerFactory<String, Item> itemConsumerFactory;

    @Autowired
    @Qualifier("shipNodeConsumerFactory")
    private ConsumerFactory<String, ShipNode> shipNodeConsumerFactory;

    @Autowired
    @Qualifier("inventoryKafkaListenerContainerFactory")
    private ConcurrentKafkaListenerContainerFactory<String, Inventory> inventoryKafkaListenerContainerFactory;

    @Autowired
    @Qualifier("itemKafkaListenerContainerFactory")
    private ConcurrentKafkaListenerContainerFactory<String, Item> itemKafkaListenerContainerFactory;

    @Autowired
    @Qualifier("shipNodeKafkaListenerContainerFactory")
    private ConcurrentKafkaListenerContainerFactory<String, ShipNode> shipNodeKafkaListenerContainerFactory;

    @Test
    void producerFactories_areCreated() {
        assertThat(inventoryProducerFactory).isNotNull();
        assertThat(itemProducerFactory).isNotNull();
        assertThat(shipNodeProducerFactory).isNotNull();
    }

    @Test
    void kafkaTemplates_areCreated() {
        assertThat(inventoryKafkaTemplate).isNotNull();
        assertThat(itemKafkaTemplate).isNotNull();
        assertThat(shipNodeKafkaTemplate).isNotNull();
    }

    @Test
    void consumerFactories_areCreated() {
        assertThat(inventoryConsumerFactory).isNotNull();
        assertThat(itemConsumerFactory).isNotNull();
        assertThat(shipNodeConsumerFactory).isNotNull();
    }

    @Test
    void listenerContainerFactories_areCreated() {
        assertThat(inventoryKafkaListenerContainerFactory).isNotNull();
        assertThat(itemKafkaListenerContainerFactory).isNotNull();
        assertThat(shipNodeKafkaListenerContainerFactory).isNotNull();
    }
}