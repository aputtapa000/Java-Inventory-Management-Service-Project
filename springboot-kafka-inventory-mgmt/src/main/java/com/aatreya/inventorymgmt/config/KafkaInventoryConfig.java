package com.aatreya.inventorymgmt.config;

import com.aatreya.inventorymgmt.model.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class KafkaInventoryConfig {

    // Producer Configurations
    @Bean
    public ProducerFactory<String, Inventory> inventoryProducerFactory(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public ProducerFactory<String, Item> itemProducerFactory(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public ProducerFactory<String, ShipNode> shipNodeProducerFactory(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    // Kafka Templates
    @Bean
    @SuppressWarnings("null")
    public KafkaTemplate<String, Inventory> inventoryKafkaTemplate(
            ProducerFactory<String, Inventory> inventoryProducerFactory) {
        return new KafkaTemplate<>(inventoryProducerFactory);
    }

    @Bean
    @SuppressWarnings("null")
    public KafkaTemplate<String, Item> itemKafkaTemplate(
            ProducerFactory<String, Item> itemProducerFactory) {
        return new KafkaTemplate<>(itemProducerFactory);
    }

    @Bean
    @SuppressWarnings("null")
    public KafkaTemplate<String, ShipNode> shipNodeKafkaTemplate(
            ProducerFactory<String, ShipNode> shipNodeProducerFactory) {
        return new KafkaTemplate<>(shipNodeProducerFactory);
    }

    // Consumer Configurations
    @Bean
    public ConsumerFactory<String, Inventory> inventoryConsumerFactory(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${spring.kafka.consumer.auto-offset-reset:earliest}") String autoOffsetReset) {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        JsonDeserializer<Inventory> valueDeserializer = new JsonDeserializer<>(Inventory.class);
        valueDeserializer.addTrustedPackages("com.aatreya.inventorymgmt.model");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), valueDeserializer);
    }

    @Bean
    public ConsumerFactory<String, Item> itemConsumerFactory(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${spring.kafka.consumer.auto-offset-reset:earliest}") String autoOffsetReset) {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        JsonDeserializer<Item> valueDeserializer = new JsonDeserializer<>(Item.class);
        valueDeserializer.addTrustedPackages("com.aatreya.inventorymgmt.model");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), valueDeserializer);
    }

    @Bean
    public ConsumerFactory<String, ShipNode> shipNodeConsumerFactory(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${spring.kafka.consumer.auto-offset-reset:earliest}") String autoOffsetReset) {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        JsonDeserializer<ShipNode> valueDeserializer = new JsonDeserializer<>(ShipNode.class);
        valueDeserializer.addTrustedPackages("com.aatreya.inventorymgmt.model");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), valueDeserializer);
    }

    // Kafka Listener Container Factories
    @Bean
    @SuppressWarnings("null")
    public ConcurrentKafkaListenerContainerFactory<String, Inventory> inventoryKafkaListenerContainerFactory(
            ConsumerFactory<String, Inventory> inventoryConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Inventory> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(inventoryConsumerFactory);
        return factory;
    }

    @Bean
    @SuppressWarnings("null")
    public ConcurrentKafkaListenerContainerFactory<String, Item> itemKafkaListenerContainerFactory(
            ConsumerFactory<String, Item> itemConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Item> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(itemConsumerFactory);
        return factory;
    }

    @Bean
    @SuppressWarnings("null")
    public ConcurrentKafkaListenerContainerFactory<String, ShipNode> shipNodeKafkaListenerContainerFactory(
            ConsumerFactory<String, ShipNode> shipNodeConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, ShipNode> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(shipNodeConsumerFactory);
        return factory;
    }
}
