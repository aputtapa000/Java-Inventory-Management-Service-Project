package com.aatreya.inventorymgmt.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

class ItemRepositoryTests {

    @Test
    void itemRepository_hasRepositoryAnnotation() {
        Repository repository = ItemRepository.class.getAnnotation(Repository.class);
        assertThat(repository).isNotNull();
    }

    @Test
    void itemRepository_extendsCosmosRepository() {
        assertThat(CosmosRepository.class.isAssignableFrom(ItemRepository.class)).isTrue();
    }
}
