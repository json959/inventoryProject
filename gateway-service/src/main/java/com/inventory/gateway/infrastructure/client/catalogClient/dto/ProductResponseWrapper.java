package com.inventory.gateway.infrastructure.client.catalogClient.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponseWrapper {
    private String type;
    private Long id;
    private ProductDto attributes;
}
