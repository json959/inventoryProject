package com.inventory.purchase.infrastructure.client.inventoryClient.dto;

import lombok.Getter;

@Getter
public class InventoryDto {

    private Long id;
    private Long productId;
    private Long quantity;
}
