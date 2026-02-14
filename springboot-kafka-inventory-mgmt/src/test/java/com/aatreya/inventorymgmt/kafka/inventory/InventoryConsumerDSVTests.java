package com.aatreya.inventorymgmt.kafka.inventory;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.aatreya.inventorymgmt.model.Inventory;
import com.aatreya.inventorymgmt.model.Item;
import com.aatreya.inventorymgmt.model.ShipNode;
import com.aatreya.inventorymgmt.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest(
        classes = {InventoryConsumerDSV.class, InventoryConsumerDSVTests.TestConfig.class},
        properties = "spring.cloud.azure.cosmos.enabled=true")
class InventoryConsumerDSVTests {

    @Autowired
    private InventoryConsumerDSV consumer;

    @Autowired
    private InventoryRepository inventoryRepository;

    @TestConfiguration
    static class TestConfig {
        @Bean
        InventoryRepository inventoryRepository() {
            return Mockito.mock(InventoryRepository.class);
        }
    }

    @BeforeEach
    void resetMocks() {
        Mockito.reset((Object) inventoryRepository);
    }

    @Test
    void consumer_validInventory_savesToRepository() {
        Inventory inventory = validInventory();

        consumer.consumer(inventory);

        verify(inventoryRepository).save(inventory);
    }

    @Test
    void consumer_missingShipNode_doesNotSave() {
        Inventory inventory = validInventory();
        inventory.setShipNode(null);

        consumer.consumer(inventory);

        verifyNoInteractions(inventoryRepository);
    }

    @Test
    void consumer_repositoryThrows_doesNotPropagateException() {
        Inventory inventory = validInventory();
        doThrow(new RuntimeException("boom")).when(inventoryRepository).save(inventory);

        assertThatCode(() -> consumer.consumer(inventory)).doesNotThrowAnyException();
        verify(inventoryRepository).save(inventory);
    }

    private Inventory validInventory() {
        return new Inventory(
                "id-1",
                new Item(),
                10,
                "upc",
                "gtin",
                "win",
                "ean",
                123L,
                new ShipNode());
    }
}