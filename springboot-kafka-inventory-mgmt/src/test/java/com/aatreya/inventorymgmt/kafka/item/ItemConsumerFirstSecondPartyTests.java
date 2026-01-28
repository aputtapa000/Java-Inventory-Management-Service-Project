package com.aatreya.inventorymgmt.kafka.item;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ItemConsumerFirstSecondParty.class)
class ItemConsumerFirstSecondPartyTests {

    @Autowired
    private ItemConsumerFirstSecondParty consumer;

    @Test
    void consumer_acceptsMessage() {
        assertThatCode(() -> consumer.consumer("message")).doesNotThrowAnyException();
    }
}