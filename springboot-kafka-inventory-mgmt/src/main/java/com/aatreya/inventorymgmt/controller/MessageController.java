package com.aatreya.inventorymgmt.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aatreya.inventorymgmt.kafka.inventory.InventoryProducer;
import com.aatreya.inventorymgmt.kafka.item.ItemProducer;
import com.aatreya.inventorymgmt.kafka.shipnode.ShipNodeProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

   
    private final InventoryProducer inventoryProducer;
    private final ItemProducer itemProducer;
    private final ShipNodeProducer shipNodeProducer;

    public MessageController(InventoryProducer inventoryProducer, ItemProducer itemProducer, ShipNodeProducer shipNodeProducer) {
        this.inventoryProducer = inventoryProducer;
        this.itemProducer = itemProducer;
        this.shipNodeProducer = shipNodeProducer;
    }
    

    @GetMapping("/inventory/publish")
    public ResponseEntity<String> inventoryPublish(@RequestParam("message") String message, @RequestParam("topic") String topic) {
        inventoryProducer.sendMessage(topic, message);
        return ResponseEntity.ok("Message sent to Kafka topic successfully");
    }

     @GetMapping("/item/publish")
    public ResponseEntity<String> itemPublish(@RequestParam("message") String message, @RequestParam("topic") String topic) {
        itemProducer.sendMessage(topic, message);
        return ResponseEntity.ok("Message sent to Kafka topic successfully");
    }

     @GetMapping("/shipnode/publish")
    public ResponseEntity<String> shipNodePublish(@RequestParam("message") String message, @RequestParam("topic") String topic) {
        shipNodeProducer.sendMessage(topic, message);
        return ResponseEntity.ok("Message sent to Kafka topic successfully");
    }

}
