package com.aatreya.inventorymgmt.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.aatreya.inventorymgmt.model.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CosmosRepository<Item, Long> {

}
