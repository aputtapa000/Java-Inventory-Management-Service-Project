package com.aatreya.inventorymgmt.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.aatreya.inventorymgmt.kafka.inventory.InventoryProducer;
import com.aatreya.inventorymgmt.kafka.item.ItemProducer;
import com.aatreya.inventorymgmt.kafka.shipnode.ShipNodeProducer;
import com.aatreya.inventorymgmt.model.Inventory;
import com.aatreya.inventorymgmt.model.ShipNode;
import com.aatreya.inventorymgmt.model.Item;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JsonMessageControllerTests {

    private InventoryProducer inventoryProducer;
    private ItemProducer itemProducer;
    private ShipNodeProducer shipNodeProducer;

    private JsonMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        inventoryProducer = Mockito.mock(InventoryProducer.class);
        itemProducer = Mockito.mock(ItemProducer.class);
        shipNodeProducer = Mockito.mock(ShipNodeProducer.class);
        objectMapper = JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
            .addModule(new JavaTimeModule())
            .build();
        JacksonJsonHttpMessageConverter jsonConverter = new JacksonJsonHttpMessageConverter(objectMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(new JsonMessageController(inventoryProducer, itemProducer, shipNodeProducer))
            .setMessageConverters(
                new StringHttpMessageConverter(),
                jsonConverter)
                .build();
    }

    @Test
    void inventoryPublish_generatesIdWhenMissing() throws Exception {
        Inventory inventory = new Inventory();
        Item item = new Item();
        ShipNode shipNode = new ShipNode();
        inventory.setItemName(item);
        inventory.setQuantityUpdate(5);
        inventory.setUPC("upc");
        inventory.setGTIN("gtin");
        inventory.setWIN("win");
        inventory.setEAN("ean");
        inventory.setISBN(1234567890L);
        inventory.setShipNode(shipNode);

        mockMvc.perform(post("/api/v1/kafka/inventory/publish")
                        .header("topic", "inventory-first-second-party-topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventory)))
                .andExpect(status().isOk())
                .andExpect(content().string("Json message sent to Kafka topic successfully"));

        ArgumentCaptor<Inventory> captor = ArgumentCaptor.forClass(Inventory.class);
        verify(inventoryProducer).sendMessage(eq("inventory-first-second-party-topic"), captor.capture());
        assertThat(captor.getValue().getUpdateId()).isNotBlank();
    }

    @Test
    void inventoryPublish_preservesExistingId() throws Exception {
        Inventory inventory = new Inventory();
        Item item = new Item();
        inventory.setUpdateId("inv-123");
        inventory.setItemName(item);
        inventory.setQuantityUpdate(5);
        inventory.setUPC("upc");
        inventory.setGTIN("gtin");
        inventory.setWIN("win");
        inventory.setEAN("ean");
        inventory.setISBN(1234567890L);
        inventory.setShipNode(new ShipNode());

        mockMvc.perform(post("/api/v1/kafka/inventory/publish")
                        .header("topic", "inventory-store-topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventory)))
                .andExpect(status().isOk())
                .andExpect(content().string("Json message sent to Kafka topic successfully"));

        ArgumentCaptor<Inventory> captor = ArgumentCaptor.forClass(Inventory.class);
        verify(inventoryProducer).sendMessage(eq("inventory-store-topic"), captor.capture());
        assertThat(captor.getValue().getUpdateId()).isEqualTo("inv-123");
    }

    @Test
    void inventoryPublish_missingTopicHeader_returnsBadRequest() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setItemName(new Item());
        inventory.setShipNode(new ShipNode());

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

    @Test
    void itemPublish_sendsMessageToProducer() throws Exception {
        Item item = new Item();
        item.setSku_id(101L);
        item.setCategory("cat-1");

        mockMvc.perform(post("/api/v1/kafka/item/publish")
                        .header("topic", "item-first-second-party-topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(content().string("Json message sent to Kafka topic successfully"));

        ArgumentCaptor<Item> captor = ArgumentCaptor.forClass(Item.class);
        verify(itemProducer).sendMessage(eq("item-first-second-party-topic"), captor.capture());
        assertThat(captor.getValue().getSku_id()).isEqualTo(101L);
    }

    @Test
    void shipNodePublish_sendsMessageToProducer() throws Exception {
        ShipNode shipNode = new ShipNode();
        shipNode.setId(55L);
        shipNode.setType("DSV");

        mockMvc.perform(post("/api/v1/kafka/shipnode/publish")
                        .header("topic", "shipnode-DSV-topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shipNode)))
                .andExpect(status().isOk())
                .andExpect(content().string("Json message sent to Kafka topic successfully"));

        ArgumentCaptor<ShipNode> captor = ArgumentCaptor.forClass(ShipNode.class);
        verify(shipNodeProducer).sendMessage(eq("shipnode-DSV-topic"), captor.capture());
        assertThat(captor.getValue().getId()).isEqualTo(55L);
    }
}