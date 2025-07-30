package com.inventory.stock.infrastructure.client.dto;

import lombok.Getter;

@Getter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
}
