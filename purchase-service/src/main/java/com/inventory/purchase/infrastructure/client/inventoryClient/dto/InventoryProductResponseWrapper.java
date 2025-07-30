package com.inventory.purchase.infrastructure.client.inventoryClient.dto;

import lombok.Getter;

@Getter
public class InventoryProductResponseWrapper {
    private String type;
    private Long id;
    private InventoryProductDto attributes;
}
