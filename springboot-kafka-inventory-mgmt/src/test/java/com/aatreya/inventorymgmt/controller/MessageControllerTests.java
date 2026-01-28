package com.aatreya.inventorymgmt.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.aatreya.inventorymgmt.kafka.item.ItemProducer;
import com.aatreya.inventorymgmt.kafka.shipnode.ShipNodeProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = {MessageController.class, MessageControllerTests.TestConfig.class})
class MessageControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ItemProducer itemProducer;

    @Autowired
    private ShipNodeProducer shipNodeProducer;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        ItemProducer itemProducer() {
            return Mockito.mock(ItemProducer.class);
        }

        @Bean
        ShipNodeProducer shipNodeProducer() {
            return Mockito.mock(ShipNodeProducer.class);
        }
    }

    @Test
    void itemPublish_returnsOkAndDelegatesToProducer() throws Exception {
        mockMvc.perform(get("/api/v1/kafka/item/publish")
                        .param("message", "hello")
                        .param("topic", "item-first-second-party-topic"))
                .andExpect(status().isOk())
                .andExpect(content().string("Message sent to Kafka topic successfully"));

        verify(itemProducer).sendMessage("item-first-second-party-topic", "hello");
    }

    @Test
    void shipNodePublish_returnsOkAndDelegatesToProducer() throws Exception {
        mockMvc.perform(get("/api/v1/kafka/shipnode/publish")
                        .param("message", "hello")
                        .param("topic", "shipnode-first-second-party-topic"))
                .andExpect(status().isOk())
                .andExpect(content().string("Message sent to Kafka topic successfully"));

        verify(shipNodeProducer).sendMessage("shipnode-first-second-party-topic", "hello");
    }

    @Test
    void itemPublish_missingParams_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/kafka/item/publish")
                        .param("message", "hello"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shipNodePublish_missingParams_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/kafka/shipnode/publish")
                        .param("topic", "shipnode-first-second-party-topic"))
                .andExpect(status().isBadRequest());
    }

}
