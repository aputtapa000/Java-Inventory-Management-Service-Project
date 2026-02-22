package com.aatreya.inventorymgmt.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "inventory")
public class Inventory {

    @Id
    private String updateId;
    private long item_sku;
    private long quantityUpdate;
    @PartitionKey
    private ShipNode shipNode;

    public Inventory() {
    }

    public Inventory(String updateId, long item_sku, long quantityUpdate, ShipNode shipNode) {
        this.updateId = updateId;
        this.item_sku = item_sku;
        this.quantityUpdate = quantityUpdate;
        this.shipNode = shipNode;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public long getItem_sku() {
        return item_sku;
    }

    public void setItem_sku(long item_sku) {
        this.item_sku = item_sku;
    }

    public long getQuantityUpdate() {
        return quantityUpdate;
    }

    public void setQuantityUpdate(long quantityUpdate) {
        this.quantityUpdate = quantityUpdate;
    }

    public ShipNode getShipNode() {
        return shipNode;
    }

    public void setShipNode(ShipNode shipNode) {
        this.shipNode = shipNode;
    }

    @Override
    public String toString() {
        return "Inventory [updateId=" + updateId + ", item_sku=" + item_sku + ", quantityUpdate=" + quantityUpdate
                + ", shipNode=" + shipNode + "]";
    }

    

}
