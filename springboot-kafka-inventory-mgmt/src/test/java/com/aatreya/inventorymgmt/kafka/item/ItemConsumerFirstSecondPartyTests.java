package com.aatreya.inventorymgmt.kafka.item;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.aatreya.inventorymgmt.model.Item;
import com.aatreya.inventorymgmt.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest(
        classes = {ItemConsumerFirstSecondParty.class, ItemConsumerFirstSecondPartyTests.TestConfig.class},
        properties = "spring.cloud.azure.cosmos.enabled=true")
class ItemConsumerFirstSecondPartyTests {

    @Autowired
    private ItemConsumerFirstSecondParty consumer;

    @Autowired
    private ItemRepository itemRepository;

    @TestConfiguration
    static class TestConfig {
        @Bean
        ItemRepository itemRepository() {
            return Mockito.mock(ItemRepository.class);
        }
    }

    @BeforeEach
    void resetMocks() {
        Mockito.reset((Object) itemRepository);
    }

    @Test
    void consumer_validItem_savesToRepository() {
        Item item = validItem();

        consumer.consumer(item);

        verify(itemRepository).save(item);
    }

    @Test
    void consumer_missingSkuId_doesNotSave() {
        Item item = validItem();
        item.setSku_id(0L);

        consumer.consumer(item);

        verifyNoInteractions(itemRepository);
    }

    @Test
    void consumer_missingCategory_doesNotSave() {
        Item item = validItem();
        item.setCategory("");

        consumer.consumer(item);

        verifyNoInteractions(itemRepository);
    }

    @Test
    void consumer_repositoryThrows_doesNotPropagateException() {
        Item item = validItem();
        doThrow(new RuntimeException("boom")).when(itemRepository).save(item);

        assertThatCode(() -> consumer.consumer(item)).doesNotThrowAnyException();
        verify(itemRepository).save(item);
    }

    private Item validItem() {
        Item item = new Item();
        item.setSku_id(200L);
        item.setCategory("cat-2");
        return item;
    }
}