package com.aatreya.inventorymgmt.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ShipNodeTests {

    @Test
    void gettersAndSetters_workAsExpected() {
        ShipNode shipNode = new ShipNode();
        shipNode.setId(101L);
        shipNode.setName("Node-A");
        shipNode.setActive(true);
        shipNode.setActiveOperation(false);
        shipNode.setEcommActiveOperation(true);
        shipNode.setType("DSV");

        assertThat(shipNode.getId()).isEqualTo(101L);
        assertThat(shipNode.getName()).isEqualTo("Node-A");
        assertThat(shipNode.isActive()).isTrue();
        assertThat(shipNode.isActiveOperation()).isFalse();
        assertThat(shipNode.isEcommActiveOperation()).isTrue();
        assertThat(shipNode.getType()).isEqualTo("DSV");
    }

    @Test
    void constructor_setsProvidedFields() {
        ShipNode shipNode = new ShipNode(202L, "Node-B", false, true, false, "Marketplace");

        assertThat(shipNode.getId()).isEqualTo(202L);
        assertThat(shipNode.getName()).isEqualTo("Node-B");
        assertThat(shipNode.isActive()).isFalse();
        assertThat(shipNode.isActiveOperation()).isTrue();
        assertThat(shipNode.isEcommActiveOperation()).isFalse();
        assertThat(shipNode.getType()).isEqualTo("Marketplace");
    }

    @Test
    void toString_includesKeyFields() {
        ShipNode shipNode = new ShipNode(303L, "Node-C", true, true, true, "Store");

        String output = shipNode.toString();
        assertThat(output).contains("id=303");
        assertThat(output).contains("name=Node-C");
        assertThat(output).contains("isActive=true");
        assertThat(output).contains("type=Store");
    }
}
