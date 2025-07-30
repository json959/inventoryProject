package com.inventory.purchase.infrastructure.client.inventoryClient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequestDTO {
    private Long productId;
    private Long quantity;
}
