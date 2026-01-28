package com.aatreya.inventorymgmt.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InventoryTests {

    @Test
    void gettersAndSetters_workAsExpected() {
        Inventory inventory = new Inventory();
        inventory.setId("id-1");
        inventory.setItemName("item");
        inventory.setQuantity(3);
        inventory.setWarehouseLocation("loc");
        inventory.setShipNode("sn-1");

        assertThat(inventory.getId()).isEqualTo("id-1");
        assertThat(inventory.getItemName()).isEqualTo("item");
        assertThat(inventory.getQuantity()).isEqualTo(3);
        assertThat(inventory.getWarehouseLocation()).isEqualTo("loc");
        assertThat(inventory.getShipNode()).isEqualTo("sn-1");
    }

    @Test
    void constructor_setsProvidedFields() {
        Inventory inventory = new Inventory("item", 5, "loc", "sn-1");

        assertThat(inventory.getItemName()).isEqualTo("item");
        assertThat(inventory.getQuantity()).isEqualTo(5);
        assertThat(inventory.getWarehouseLocation()).isEqualTo("loc");
        assertThat(inventory.getShipNode()).isEqualTo("sn-1");
    }

    @Test
    void toString_includesKeyFields() {
        Inventory inventory = new Inventory("item", 2, "loc", "sn-1");
        inventory.setId("id-1");

        assertThat(inventory.toString()).contains("id=id-1");
        assertThat(inventory.toString()).contains("itemName=item");
        assertThat(inventory.toString()).contains("quantity=2");
        assertThat(inventory.toString()).contains("warehouseLocation=loc");
        assertThat(inventory.toString()).contains("shipNode=sn-1");
    }
}