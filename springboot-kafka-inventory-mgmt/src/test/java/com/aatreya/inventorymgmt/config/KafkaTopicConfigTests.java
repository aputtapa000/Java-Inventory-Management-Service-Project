package com.aatreya.inventorymgmt.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = KafkaTopicConfig.class)
class KafkaTopicConfigTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void inventoryTopics_areConfigured() {
        NewTopic firstSecond = applicationContext.getBean("inventoryFirstSecondPartyTopic", NewTopic.class);
        NewTopic dsv = applicationContext.getBean("inventoryDsvTopic", NewTopic.class);
        NewTopic marketplace = applicationContext.getBean("inventoryMarketplaceTopic", NewTopic.class);
        NewTopic store = applicationContext.getBean("inventoryStoreTopic", NewTopic.class);

        assertThat(firstSecond.name()).isEqualTo("inventory-first-second-party-topic");
        assertThat(dsv.name()).isEqualTo("inventory-DSV-topic");
        assertThat(marketplace.name()).isEqualTo("inventory-marketplace-topic");
        assertThat(store.name()).isEqualTo("inventory-store-topic");
    }

    @Test
    void itemTopics_areConfigured() {
        NewTopic firstSecond = applicationContext.getBean("itemFirstSecondPartyTopic", NewTopic.class);
        NewTopic dsv = applicationContext.getBean("itemDSVTopic", NewTopic.class);
        NewTopic marketplace = applicationContext.getBean("itemMarketplaceTopic", NewTopic.class);

        assertThat(firstSecond.name()).isEqualTo("item-first-second-party-topic");
        assertThat(dsv.name()).isEqualTo("item-DSV-topic");
        assertThat(marketplace.name()).isEqualTo("item-marketplace-topic");
    }

    @Test
    void shipNodeTopics_areConfigured() {
        NewTopic firstSecond = applicationContext.getBean("shipNodeFirstSecondPartyTopic", NewTopic.class);
        NewTopic dsv = applicationContext.getBean("shipNodeDSVTopic", NewTopic.class);
        NewTopic marketplace = applicationContext.getBean("shipNodeMarketplaceTopic", NewTopic.class);

        assertThat(firstSecond.name()).isEqualTo("shipnode-first-second-party-topic");
        assertThat(dsv.name()).isEqualTo("shipnode-DSV-topic");
        assertThat(marketplace.name()).isEqualTo("shipnode-marketplace-topic");
    }
}