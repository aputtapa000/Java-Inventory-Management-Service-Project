package com.aatreya.inventorymgmt.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.aatreya.inventorymgmt.kafka.inventory.InventoryProducer;
import com.aatreya.inventorymgmt.model.Inventory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JsonMessageControllerTests {

    private InventoryProducer inventoryProducer;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        inventoryProducer = Mockito.mock(InventoryProducer.class);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(new JsonMessageController(inventoryProducer)).build();
    }

    @Test
    void inventoryPublish_generatesIdWhenMissing() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setItemName("item");
        inventory.setQuantity(5);
        inventory.setWarehouseLocation("loc");
        inventory.setShipNode("sn-1");

        mockMvc.perform(post("/api/v1/kafka/inventory/publish")
                        .header("topic", "inventory-first-second-party-topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventory)))
                .andExpect(status().isOk())
                .andExpect(content().string("Json message sent to Kafka topic successfully"));

        ArgumentCaptor<Inventory> captor = ArgumentCaptor.forClass(Inventory.class);
        verify(inventoryProducer).sendMessage(eq("inventory-first-second-party-topic"), captor.capture());
        assertThat(captor.getValue().getId()).isNotBlank();
    }

    @Test
    void inventoryPublish_preservesExistingId() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setId("inv-123");
        inventory.setItemName("item");
        inventory.setQuantity(5);
        inventory.setWarehouseLocation("loc");
        inventory.setShipNode("sn-1");

        mockMvc.perform(post("/api/v1/kafka/inventory/publish")
                        .header("topic", "inventory-store-topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventory)))
                .andExpect(status().isOk())
                .andExpect(content().string("Json message sent to Kafka topic successfully"));

        ArgumentCaptor<Inventory> captor = ArgumentCaptor.forClass(Inventory.class);
        verify(inventoryProducer).sendMessage(eq("inventory-store-topic"), captor.capture());
        assertThat(captor.getValue().getId()).isEqualTo("inv-123");
    }

    @Test
    void inventoryPublish_missingTopicHeader_returnsBadRequest() throws Exception {
        Inventory inventory = new Inventory("item", 1, "loc", "sn-1");

        mockMvc.perform(post("/api/v1/kafka/inventory/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventory)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void inventoryPublish_missingBody_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/kafka/inventory/publish")
                        .header("topic", "inventory-store-topic")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}