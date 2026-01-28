package com.aatreya.inventorymgmt.kafka.item;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ItemConsumerDSV.class)
class ItemConsumerDSVTests {

    @Autowired
    private ItemConsumerDSV consumer;

    @Test
    void consumer_acceptsMessage() {
        assertThatCode(() -> consumer.consumer("message")).doesNotThrowAnyException();
    }
}