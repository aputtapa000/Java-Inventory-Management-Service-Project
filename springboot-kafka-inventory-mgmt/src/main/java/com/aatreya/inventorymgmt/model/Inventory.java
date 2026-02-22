package com.aatreya.inventorymgmt.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

@Container(containerName = "inventory")
public class Inventory {
    @NotBlank
    @Id
    private String updateId;
    @NotNull
    private Long item_sku;
    @NotNull
    private Long quantityUpdate;
    @NotNull
    @PartitionKey
    private ShipNode shipNode;

    public Inventory() {
    }

    public Inventory(String updateId, Long item_sku, Long quantityUpdate, ShipNode shipNode) {
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

    public Long getItem_sku() {
        return item_sku;
    }

    public void setItem_sku(Long item_sku) {
        this.item_sku = item_sku;
    }

    public Long getQuantityUpdate() {
        return quantityUpdate;
    }

    public void setQuantityUpdate(Long quantityUpdate) {
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
