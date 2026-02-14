package com.aatreya.inventorymgmt.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InventoryTests {

    @Test
    void gettersAndSetters_workAsExpected() {
        Inventory inventory = new Inventory();
        Item item = new Item();
        ShipNode shipNode = new ShipNode();
        inventory.setUpdateId("id-1");
        inventory.setItemName(item);
        inventory.setQuantityUpdate(3);
        inventory.setUPC("upc");
        inventory.setGTIN("gtin");
        inventory.setWIN("win");
        inventory.setEAN("ean");
        inventory.setISBN(1234567890L);
        inventory.setShipNode(shipNode);

        assertThat(inventory.getUpdateId()).isEqualTo("id-1");
        assertThat(inventory.getItemName()).isSameAs(item);
        assertThat(inventory.getQuantityUpdate()).isEqualTo(3);
        assertThat(inventory.getUPC()).isEqualTo("upc");
        assertThat(inventory.getGTIN()).isEqualTo("gtin");
        assertThat(inventory.getWIN()).isEqualTo("win");
        assertThat(inventory.getEAN()).isEqualTo("ean");
        assertThat(inventory.getISBN()).isEqualTo(1234567890L);
        assertThat(inventory.getShipNode()).isSameAs(shipNode);
    }

    @Test
    void constructor_setsProvidedFields() {
        Item item = new Item();
        ShipNode shipNode = new ShipNode();
        Inventory inventory = new Inventory(
                "id-1",
                item,
                5,
                "upc",
                "gtin",
                "win",
                "ean",
                1234567890L,
                shipNode);

        assertThat(inventory.getUpdateId()).isEqualTo("id-1");
        assertThat(inventory.getItemName()).isSameAs(item);
        assertThat(inventory.getQuantityUpdate()).isEqualTo(5);
        assertThat(inventory.getUPC()).isEqualTo("upc");
        assertThat(inventory.getGTIN()).isEqualTo("gtin");
        assertThat(inventory.getWIN()).isEqualTo("win");
        assertThat(inventory.getEAN()).isEqualTo("ean");
        assertThat(inventory.getISBN()).isEqualTo(1234567890L);
        assertThat(inventory.getShipNode()).isSameAs(shipNode);
    }

    @Test
    void toString_includesKeyFields() {
        Item item = new Item();
        ShipNode shipNode = new ShipNode();
        Inventory inventory = new Inventory(
                "id-1",
                item,
                2,
                "upc",
                "gtin",
                "win",
                "ean",
                1234567890L,
                shipNode);

        assertThat(inventory.toString()).contains("updateId=id-1");
        assertThat(inventory.toString()).contains("itemName=" + item);
        assertThat(inventory.toString()).contains("quantityUpdate=2");
        assertThat(inventory.toString()).contains("UPC=upc");
        assertThat(inventory.toString()).contains("GTIN=gtin");
        assertThat(inventory.toString()).contains("WIN=win");
        assertThat(inventory.toString()).contains("EAN=ean");
        assertThat(inventory.toString()).contains("ISBN=1234567890");
        assertThat(inventory.toString()).contains("shipNode=" + shipNode);
    }
}