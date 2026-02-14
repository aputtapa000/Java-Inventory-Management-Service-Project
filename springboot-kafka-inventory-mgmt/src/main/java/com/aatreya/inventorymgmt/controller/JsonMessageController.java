package com.aatreya.inventorymgmt.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aatreya.inventorymgmt.kafka.inventory.InventoryProducer;
import com.aatreya.inventorymgmt.kafka.item.ItemProducer;
import com.aatreya.inventorymgmt.kafka.shipnode.ShipNodeProducer;
import com.aatreya.inventorymgmt.model.Inventory;
import com.aatreya.inventorymgmt.model.Item;
import com.aatreya.inventorymgmt.model.ShipNode;

import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private final InventoryProducer inventoryProducer;
    private final ItemProducer itemProducer;
    private final ShipNodeProducer shipNodeProducer;

    public JsonMessageController(InventoryProducer inventoryProducer, ItemProducer itemProducer, ShipNodeProducer shipNodeProducer) {
        this.inventoryProducer = inventoryProducer;
        this.itemProducer = itemProducer;
        this.shipNodeProducer = shipNodeProducer;
    }

    @PostMapping("/inventory/publish")
    public ResponseEntity<String> inventoryPublish(
            @RequestBody Inventory inventory, 
            @RequestHeader("topic") String topic) {
        
        // Generate UUID at the service layer before sending to Kafka
        // This ensures the same ID is used throughout the entire flow
        if (inventory.getUpdateId() == null || inventory.getUpdateId().isEmpty()) {
            inventory.setUpdateId(UUID.randomUUID().toString());
        }
        
        inventoryProducer.sendMessage(topic, inventory);
        return ResponseEntity.ok("Json message sent to Kafka topic successfully");
    }

    @PostMapping("/item/publish")
    public ResponseEntity<String> itemPublish(@RequestBody Item item, @RequestHeader("topic") String topic) {
        itemProducer.sendMessage(topic, item);
        return ResponseEntity.ok("Json message sent to Kafka topic successfully");
    }

    @PostMapping("/shipnode/publish")
    public ResponseEntity<String> shipNodePublish(@RequestBody ShipNode shipNode, @RequestHeader("topic") String topic) {
        shipNodeProducer.sendMessage(topic, shipNode);
        return ResponseEntity.ok("Json message sent to Kafka topic successfully");
    }
    

}

