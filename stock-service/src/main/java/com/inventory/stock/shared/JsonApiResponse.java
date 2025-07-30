package com.inventory.stock.shared;

import com.inventory.stock.domain.model.Inventory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JsonApiResponse {
    private final String type;
    private final Object id;
    private final Object attributes;
}
