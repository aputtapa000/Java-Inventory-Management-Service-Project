package com.aatreya.inventorymgmt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "spring.cloud.azure.cosmos.enabled=false",
    "spring.kafka.admin.auto-create=false",
    "spring.kafka.admin.fail-fast=false"
})
class SpringbootKafkaApplicationTests {

    @Test
    void contextLoads_doesNotThrow() {
        assertDoesNotThrow(this::contextLoads);
    }

    void contextLoads() {
    }
}