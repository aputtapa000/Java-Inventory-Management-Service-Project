package com.aatreya.inventorymgmt.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

class ShipNodeRepositoryTests {

    @Test
    void shipNodeRepository_hasRepositoryAnnotation() {
        Repository repository = ShipNodeRepository.class.getAnnotation(Repository.class);
        assertThat(repository).isNotNull();
    }

    @Test
    void shipNodeRepository_extendsCosmosRepository() {
        assertThat(CosmosRepository.class.isAssignableFrom(ShipNodeRepository.class)).isTrue();
    }
}
