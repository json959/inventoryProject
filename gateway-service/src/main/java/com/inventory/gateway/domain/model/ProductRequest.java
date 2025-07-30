package com.inventory.gateway.domain.model;

import lombok.Getter;

@Getter
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
}
