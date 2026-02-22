package com.aatreya.inventorymgmt.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InventoryTests {

    @Test
    void gettersAndSetters_workAsExpected() {
        Inventory inventory = new Inventory();
        ShipNode shipNode = new ShipNode();
        inventory.setUpdateId("id-1");
        inventory.setItem_sku(20);;
        inventory.setQuantityUpdate(3);
        inventory.setShipNode(shipNode);

        assertThat(inventory.getUpdateId()).isEqualTo("id-1");
        assertThat(inventory.getItem_sku()).isEqualTo(20);
        assertThat(inventory.getQuantityUpdate()).isEqualTo(3);
        assertThat(inventory.getShipNode()).isSameAs(shipNode);
    }

    @Test
    void constructor_setsProvidedFields() {
        ShipNode shipNode = new ShipNode();
        Inventory inventory = new Inventory(
                "id-1",
                20L,
                5,
                shipNode);

        assertThat(inventory.getUpdateId()).isEqualTo("id-1");
        assertThat(inventory.getItem_sku()).isEqualTo(20);
        assertThat(inventory.getQuantityUpdate()).isEqualTo(5);
        assertThat(inventory.getShipNode()).isSameAs(shipNode);
    }

    @Test
    void toString_includesKeyFields() {
        ShipNode shipNode = new ShipNode();
        Inventory inventory = new Inventory(
                "id-1",
                20L,
                2,
                shipNode);

        assertThat(inventory.toString()).contains("updateId=id-1");
        assertThat(inventory.toString()).contains("item_sku=20");
        assertThat(inventory.toString()).contains("quantityUpdate=2");
        assertThat(inventory.toString()).contains("shipNode=" + shipNode);
    }
}