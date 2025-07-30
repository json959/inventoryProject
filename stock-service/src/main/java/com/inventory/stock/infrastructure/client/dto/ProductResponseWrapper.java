package com.inventory.stock.infrastructure.client.dto;

import lombok.Getter;

@Getter
public class ProductResponseWrapper {
    private String type;
    private Long id;
    private ProductDto attributes;
}
