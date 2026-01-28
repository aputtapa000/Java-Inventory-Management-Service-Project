package com.aatreya.inventorymgmt.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.aatreya.inventorymgmt.model.Inventory;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CosmosRepository<Inventory, Integer> {

}
