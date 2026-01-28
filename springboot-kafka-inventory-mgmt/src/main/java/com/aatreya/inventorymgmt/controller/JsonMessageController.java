package com.aatreya.inventorymgmt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aatreya.inventorymgmt.kafka.inventory.InventoryProducer;
import com.aatreya.inventorymgmt.model.Inventory;



@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private final InventoryProducer inventoryProducer;


    public JsonMessageController(InventoryProducer inventoryProducer) {
    this.inventoryProducer = inventoryProducer;
    } 

    @PostMapping("/inventory/publish")
    public ResponseEntity<String> inventoryPublish(@RequestBody Inventory inventory, @Header("topic") String topic) {
        inventoryProducer.sendMessage(topic, inventory);
        return ResponseEntity.ok("Json message sent to Kafka topic successfully");
    }

}

