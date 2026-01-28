package com.aatreya.inventorymgmt.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

class InventoryRepositoryTests {

    @Test
    void inventoryRepository_hasRepositoryAnnotation() {
        Repository repository = InventoryRepository.class.getAnnotation(Repository.class);
        assertThat(repository).isNotNull();
    }

    @Test
    void inventoryRepository_extendsCosmosRepository() {
        assertThat(CosmosRepository.class.isAssignableFrom(InventoryRepository.class)).isTrue();
    }
}