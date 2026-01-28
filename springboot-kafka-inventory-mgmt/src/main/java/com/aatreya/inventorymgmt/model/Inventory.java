package com.aatreya.inventorymgmt.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import org.springframework.data.annotation.Id;

@Container(containerName = "inventory")
public class Inventory {

    @Id
    public int id;
    public String itemName;
    public int quantity;
    public String warehouseLocation;
    public String shipNode;


    public Inventory(int id, String itemName, int quantity, String warehouseLocation, String shipNode) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.warehouseLocation = warehouseLocation;
        this.shipNode = shipNode;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
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
