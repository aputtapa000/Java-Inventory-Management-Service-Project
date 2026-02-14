package com.aatreya.inventorymgmt.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "inventory")
public class Inventory {

    @Id
    private String updateId;
    private Item itemName;
    private long quantityUpdate;
    private String UPC;
    private String GTIN;
    private String WIN;
    private String EAN;
    private long ISBN;
    @PartitionKey
    private ShipNode shipNode;

    public Inventory() {
    }

    public Inventory(String updateId, Item itemName, long quantityUpdate, String uPC, String gTIN, String wIN,
            String eAN, long iSBN, ShipNode shipNode) {
        this.updateId = updateId;
        this.itemName = itemName;
        this.quantityUpdate = quantityUpdate;
        UPC = uPC;
        GTIN = gTIN;
        WIN = wIN;
        EAN = eAN;
        ISBN = iSBN;
        this.shipNode = shipNode;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Item getItemName() {
        return itemName;
    }

    public void setItemName(Item itemName) {
        this.itemName = itemName;
    }

    public long getQuantityUpdate() {
        return quantityUpdate;
    }

    public void setQuantityUpdate(long quantityUpdate) {
        this.quantityUpdate = quantityUpdate;
    }

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String uPC) {
        UPC = uPC;
    }

    public String getGTIN() {
        return GTIN;
    }

    public void setGTIN(String gTIN) {
        GTIN = gTIN;
    }

    public String getWIN() {
        return WIN;
    }

    public void setWIN(String wIN) {
        WIN = wIN;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String eAN) {
        EAN = eAN;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long iSBN) {
        ISBN = iSBN;
    }

    public ShipNode getShipNode() {
        return shipNode;
    }

    public void setShipNode(ShipNode shipNode) {
        this.shipNode = shipNode;
    }

    @Override
    public String toString() {
        return "Inventory [updateId=" + updateId + ", itemName=" + itemName + ", quantityUpdate=" + quantityUpdate
                + ", UPC=" + UPC + ", GTIN=" + GTIN + ", WIN=" + WIN + ", EAN=" + EAN + ", ISBN=" + ISBN + ", shipNode="
                + shipNode + "]";
    }

}
