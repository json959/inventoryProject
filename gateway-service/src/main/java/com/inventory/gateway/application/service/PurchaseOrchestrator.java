package com.inventory.gateway.application.service;

import com.inventory.gateway.domain.model.InventoryRequest;
import com.inventory.gateway.domain.model.PurchaseRequest;
import com.inventory.gateway.infrastructure.client.purchaseClient.PurchaseClient;
import com.inventory.gateway.infrastructure.client.purchaseClient.dto.PurchaseDto;
import com.inventory.gateway.infrastructure.client.purchaseClient.dto.PurchaseResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PurchaseOrchestrator {


    private PurchaseClient purchaseClient;
    private InventoryOrchestrator inventoryOrchestrator;



    public PurchaseOrchestrator(InventoryOrchestrator inventoryOrchestrator, PurchaseClient purchaseClient) {
        this.inventoryOrchestrator = inventoryOrchestrator;
        this.purchaseClient = purchaseClient;
    }

    public PurchaseResponseWrapper newPurchase(PurchaseRequest purchase){
        Long newQuantity;
        InventoryRequest request = new InventoryRequest();
        Long quantity = inventoryOrchestrator.getInventoryByproduct(purchase.getProductId()).getQuantity();

        if (quantity< purchase.getQuantityPurchased()){
            throw new RuntimeException("Not enough stock for product ID: " + purchase.getProductId());
        }else{
            newQuantity = quantity -purchase.getQuantityPurchased();
        }

        request.setProductId(purchase.getProductId());
        request.setQuantity(newQuantity);
        inventoryOrchestrator.updateQuantity(request);
        return purchaseClient.NewPurchase(purchase);
    }

    public List<PurchaseDto> getPurchasesProduct(Long id){
        return purchaseClient.PurchasesProduct(id);
    }




}
