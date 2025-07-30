package com.inventory.gateway.infrastructure.client.purchaseClient.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseWrapper {
    private  String type;
    private  Object id;
    private  Object date;
    private  Object attributes;
}
