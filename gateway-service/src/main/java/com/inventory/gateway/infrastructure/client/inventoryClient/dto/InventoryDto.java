package com.inventory.gateway.infrastructure.client.inventoryClient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

    private Long id;
    private Long productId;
    private Long quantity;
}
