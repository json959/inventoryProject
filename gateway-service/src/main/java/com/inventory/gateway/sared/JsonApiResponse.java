package com.inventory.gateway.sared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JsonApiResponse {
    private final String type;
    private final Object id;
    private final Object attributes;
}
