package com.aatreya.inventorymgmt.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    // Inventory Management Topics
    @Bean
    public NewTopic inventoryFirstPartyTopic() {
        return TopicBuilder.name("inventory-first-party-topic").build();
    }

    @Bean
    public NewTopic inventoryDsvTopic() {
        return TopicBuilder.name("inventory-DSV-topic").build();
    }

    @Bean
    public NewTopic inventoryMarketplaceTopic() {
        return TopicBuilder.name("inventory-marketplace-topic").build();
    }

    @Bean
    public NewTopic inventoryStoreTopic() {
        return TopicBuilder.name("inventory-store-topic").build();
    }
    
    
    //Item (Catalog Feed) Topics
    
    @Bean
    public NewTopic itemFirstSecondPartyTopic() {
        return TopicBuilder.name("item-first-second-party-topic").build();
    }

    @Bean
    public NewTopic itemDSVTopic() {
        return TopicBuilder.name("item-DSV-topic").build();
    }

    @Bean
    public NewTopic itemMarketplaceTopic() {
        return TopicBuilder.name("item-marketplace-topic").build();
    }

    //ShipNode Topics

    @Bean
    public NewTopic shipNodeFirstSecondPartyTopic() {
        return TopicBuilder.name("shipnode-first-second-party-topic").build();
    }

    @Bean
    public NewTopic shipNodeDSVTopic() {
        return TopicBuilder.name("shipnode-DSV-topic").build();
    }

    @Bean
    public NewTopic shipNodeMarketplaceTopic() {
        return TopicBuilder.name("shipnode-marketplace-topic").build();
    }

}
