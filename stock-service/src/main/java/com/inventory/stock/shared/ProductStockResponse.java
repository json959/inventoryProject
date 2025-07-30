package com.inventory.stock.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductStockResponse {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Long quantityAvailable;
}
