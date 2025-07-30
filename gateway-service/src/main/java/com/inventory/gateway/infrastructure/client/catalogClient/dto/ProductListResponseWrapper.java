package com.inventory.gateway.infrastructure.client.catalogClient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ProductListResponseWrapper {
    private String type;
    private Long id;
    private List<ProductDto> attributes;
    }

