package com.aatreya.inventorymgmt.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "inventory")
public class Inventory {

    @Id
    public String id;
    public String itemName;
    public int quantity;
    public String warehouseLocation;
    @PartitionKey
    public String shipNode;

    // Default constructor required for JSON deserialization
    public Inventory() {
    }

    public Inventory(String itemName, int quantity, String warehouseLocation, String shipNode) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.warehouseLocation = warehouseLocation;
        this.shipNode = shipNode;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getItemName() {
        return itemName;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getWarehouseLocation() {
        return warehouseLocation;
    }


    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }


    public String getShipNode() {
        return shipNode;
    }


    public void setShipNode(String shipNode) {
        this.shipNode = shipNode;
    }


    @Override
    public String toString() {
        return "Inventory [id=" + id + ", itemName=" + itemName + ", quantity=" + quantity + ", warehouseLocation="
                + warehouseLocation + ", shipNode=" + shipNode + "]";
    }
    
    

}
