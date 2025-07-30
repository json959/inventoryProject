package com.inventory.gateway.infrastructure.client.inventoryClient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InventoryResponseWrapper {
    private String type;
    private Long id;
    private InventoryDto attributes;
}
