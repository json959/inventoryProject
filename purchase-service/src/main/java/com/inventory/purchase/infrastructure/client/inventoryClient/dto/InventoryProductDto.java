package com.inventory.purchase.infrastructure.client.inventoryClient.dto;

import lombok.*;


@Getter
public class InventoryProductDto {

    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Long quantityAvailable;
}
