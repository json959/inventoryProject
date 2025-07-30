package com.inventory.gateway.infrastructure.client.inventoryClient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProductDto {

    private Long id;
    private Long productId;
    private String name;
    private double price;
    private String description;
    private Long quantity;
}
