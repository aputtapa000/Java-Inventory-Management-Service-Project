package com.aatreya.inventorymgmt.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import java.time.LocalDate;

@Container(containerName = "SKU")
public class Item {
    @NotNull
    @Id
    private Long sku_id;
    @NotBlank
    private String name;
    private String UPC;
    private String GTIN;
    private String WIN;
    private String EAN;
    private Long ISBN;
    @NotBlank
    @PartitionKey
    private String category;
    private Boolean isActive;
    private Boolean isPreOrder;
    private LocalDate preOrderStartDate;
    private LocalDate preOrderEndDate;
    private Boolean isBackOrder;
    private LocalDate backOrderStartDate;
    private LocalDate backOrderEndDate;
    private LocalDate ecommActiveDate;
    private Boolean marketplaceEnabled;

    public Item() {
    }

    public Item(Long sku_id, String name, String uPC, String gTIN, String wIN, String eAN, Long iSBN, String category,
            Boolean isActive, Boolean isPreOrder, LocalDate preOrderStartDate, LocalDate preOrderEndDate,
            Boolean isBackOrder, LocalDate backOrderStartDate, LocalDate backOrderEndDate, LocalDate ecommActiveDate,
            Boolean marketplaceEnabled) {
        this.sku_id = sku_id;
        this.name = name;
        UPC = uPC;
        GTIN = gTIN;
        WIN = wIN;
        EAN = eAN;
        ISBN = iSBN;
        this.category = category;
        this.isActive = isActive;
        this.isPreOrder = isPreOrder;
        this.preOrderStartDate = preOrderStartDate;
        this.preOrderEndDate = preOrderEndDate;
        this.isBackOrder = isBackOrder;
        this.backOrderStartDate = backOrderStartDate;
        this.backOrderEndDate = backOrderEndDate;
        this.ecommActiveDate = ecommActiveDate;
        this.marketplaceEnabled = marketplaceEnabled;
    }

    public Long getSku_id() {
        return sku_id;
    }

    public void setSku_id(Long sku_id) {
        this.sku_id = sku_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long iSBN) {
        ISBN = iSBN;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isPreOrder() {
        return isPreOrder;
    }

    public void setPreOrder(Boolean isPreOrder) {
        this.isPreOrder = isPreOrder;
    }

    public LocalDate getPreOrderStartDate() {
        return preOrderStartDate;
    }

    public void setPreOrderStartDate(LocalDate preOrderStartDate) {
        this.preOrderStartDate = preOrderStartDate;
    }

    public LocalDate getPreOrderEndDate() {
        return preOrderEndDate;
    }

    public void setPreOrderEndDate(LocalDate preOrderEndDate) {
        this.preOrderEndDate = preOrderEndDate;
    }

    public Boolean isBackOrder() {
        return isBackOrder;
    }

    public void setBackOrder(Boolean isBackOrder) {
        this.isBackOrder = isBackOrder;
    }

    public LocalDate getBackOrderStartDate() {
        return backOrderStartDate;
    }

    public void setBackOrderStartDate(LocalDate backOrderStartDate) {
        this.backOrderStartDate = backOrderStartDate;
    }

    public LocalDate getBackOrderEndDate() {
        return backOrderEndDate;
    }

    public void setBackOrderEndDate(LocalDate backOrderEndDate) {
        this.backOrderEndDate = backOrderEndDate;
    }

    public LocalDate getEcommActiveDate() {
        return ecommActiveDate;
    }

    public void setEcommActiveDate(LocalDate ecommActiveDate) {
        this.ecommActiveDate = ecommActiveDate;
    }

    public Boolean isMarketplaceEnabled() {
        return marketplaceEnabled;
    }

    public void setMarketplaceEnabled(Boolean marketplaceEnabled) {
        this.marketplaceEnabled = marketplaceEnabled;
    }

    @Override
    public String toString() {
        return "Item [sku_id=" + sku_id + ", name=" + name + ", UPC=" + UPC + ", GTIN=" + GTIN + ", WIN=" + WIN
                + ", EAN=" + EAN + ", ISBN=" + ISBN + ", category=" + category + ", isActive=" + isActive
                + ", isPreOrder=" + isPreOrder + ", preOrderStartDate=" + preOrderStartDate + ", preOrderEndDate="
                + preOrderEndDate + ", isBackOrder=" + isBackOrder + ", backOrderStartDate=" + backOrderStartDate
                + ", backOrderEndDate=" + backOrderEndDate + ", ecommActiveDate=" + ecommActiveDate
                + ", marketplaceEnabled=" + marketplaceEnabled + "]";
    }

}
