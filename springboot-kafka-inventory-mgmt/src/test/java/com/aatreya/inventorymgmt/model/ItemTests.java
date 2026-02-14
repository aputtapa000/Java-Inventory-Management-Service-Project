package com.aatreya.inventorymgmt.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class ItemTests {

    @Test
    void gettersAndSetters_workAsExpected() {
        Item item = new Item();
        LocalDate now = LocalDate.of(2024, 1, 15);
        item.setSku_id(123L);
        item.setName("Widget");
        item.setUPC("upc");
        item.setGTIN("gtin");
        item.setWIN("win");
        item.setEAN("ean");
        item.setISBN(9876543210L);
        item.setCategory("cat-1");
        item.setActive(true);
        item.setPreOrder(true);
        item.setPreOrderStartDate(now);
        item.setPreOrderEndDate(now.plusDays(1));
        item.setBackOrder(true);
        item.setBackOrderStartDate(now.plusDays(2));
        item.setBackOrderEndDate(now.plusDays(3));
        item.setEcommActiveDate(now.plusDays(4));
        item.setMarketplaceEnabled(true);

        assertThat(item.getSku_id()).isEqualTo(123L);
        assertThat(item.getName()).isEqualTo("Widget");
        assertThat(item.getUPC()).isEqualTo("upc");
        assertThat(item.getGTIN()).isEqualTo("gtin");
        assertThat(item.getWIN()).isEqualTo("win");
        assertThat(item.getEAN()).isEqualTo("ean");
        assertThat(item.getISBN()).isEqualTo(9876543210L);
        assertThat(item.getCategory()).isEqualTo("cat-1");
        assertThat(item.isActive()).isTrue();
        assertThat(item.isPreOrder()).isTrue();
        assertThat(item.getPreOrderStartDate()).isEqualTo(now);
        assertThat(item.getPreOrderEndDate()).isEqualTo(now.plusDays(1));
        assertThat(item.isBackOrder()).isTrue();
        assertThat(item.getBackOrderStartDate()).isEqualTo(now.plusDays(2));
        assertThat(item.getBackOrderEndDate()).isEqualTo(now.plusDays(3));
        assertThat(item.getEcommActiveDate()).isEqualTo(now.plusDays(4));
        assertThat(item.isMarketplaceEnabled()).isTrue();
    }

    @Test
    void constructor_setsProvidedFields() {
        LocalDate now = LocalDate.of(2024, 2, 2);
        Item item = new Item(
                456L,
                "Gizmo",
                "upc",
                "gtin",
                "win",
                "ean",
                111L,
                "cat-2",
                true,
                false,
                now,
                now.plusDays(1),
                true,
                now.plusDays(2),
                now.plusDays(3),
                now.plusDays(4),
                false);

        assertThat(item.getSku_id()).isEqualTo(456L);
        assertThat(item.getName()).isEqualTo("Gizmo");
        assertThat(item.getUPC()).isEqualTo("upc");
        assertThat(item.getGTIN()).isEqualTo("gtin");
        assertThat(item.getWIN()).isEqualTo("win");
        assertThat(item.getEAN()).isEqualTo("ean");
        assertThat(item.getISBN()).isEqualTo(111L);
        assertThat(item.getCategory()).isEqualTo("cat-2");
        assertThat(item.isActive()).isTrue();
        assertThat(item.isPreOrder()).isFalse();
        assertThat(item.getPreOrderStartDate()).isEqualTo(now);
        assertThat(item.getPreOrderEndDate()).isEqualTo(now.plusDays(1));
        assertThat(item.isBackOrder()).isTrue();
        assertThat(item.getBackOrderStartDate()).isEqualTo(now.plusDays(2));
        assertThat(item.getBackOrderEndDate()).isEqualTo(now.plusDays(3));
        assertThat(item.getEcommActiveDate()).isEqualTo(now.plusDays(4));
        assertThat(item.isMarketplaceEnabled()).isFalse();
    }

    @Test
    void toString_includesKeyFields() {
        LocalDate now = LocalDate.of(2024, 3, 3);
        Item item = new Item(
                789L,
                "Thing",
                "upc",
                "gtin",
                "win",
                "ean",
                222L,
                "cat-3",
                true,
                false,
                now,
                now.plusDays(1),
                false,
                now.plusDays(2),
                now.plusDays(3),
                now.plusDays(4),
                true);

        String output = item.toString();
        assertThat(output).contains("sku_id=789");
        assertThat(output).contains("name=Thing");
        assertThat(output).contains("category=cat-3");
        assertThat(output).contains("marketplaceEnabled=true");
    }
}
