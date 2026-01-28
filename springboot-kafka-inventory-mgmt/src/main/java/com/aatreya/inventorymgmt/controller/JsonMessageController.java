package com.aatreya.inventorymgmt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aatreya.inventorymgmt.kafka.inventory.InventoryProducer;
import com.aatreya.inventorymgmt.model.Inventory;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private final InventoryProducer inventoryProducer;

    public JsonMessageController(InventoryProducer inventoryProducer) {
        this.inventoryProducer = inventoryProducer;
    } 

    @PostMapping("/inventory/publish")
    public ResponseEntity<String> inventoryPublish(
            @RequestBody Inventory inventory, 
            @RequestHeader("topic") String topic) {
        
        // Generate UUID at the service layer before sending to Kafka
        // This ensures the same ID is used throughout the entire flow
        if (inventory.getId() == null || inventory.getId().isEmpty()) {
            inventory.setId(UUID.randomUUID().toString());
        }
        
        inventoryProducer.sendMessage(topic, inventory);
        return ResponseEntity.ok("Json message sent to Kafka topic successfully");
    }

}

