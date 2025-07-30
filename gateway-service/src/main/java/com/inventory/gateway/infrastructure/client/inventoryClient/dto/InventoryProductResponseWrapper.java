package com.inventory.gateway.infrastructure.client.inventoryClient.dto;

import lombok.Getter;

@Getter
public class InventoryProductResponseWrapper {
    private String type;
    private Long id;
    private InventoryProductDto attributes;
}
