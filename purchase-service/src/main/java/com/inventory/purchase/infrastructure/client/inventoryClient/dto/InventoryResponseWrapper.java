package com.inventory.purchase.infrastructure.client.inventoryClient.dto;

import lombok.Getter;

@Getter
public class InventoryResponseWrapper {
    private String type;
    private Long id;
    private InventoryDto attributes;
}
