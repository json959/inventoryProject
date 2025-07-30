package com.inventory.gateway.application.service;

import com.inventory.gateway.domain.model.InventoryRequest;
import com.inventory.gateway.infrastructure.client.catalogClient.dto.ProductDto;
import com.inventory.gateway.infrastructure.client.inventoryClient.InventoryClient;
import com.inventory.gateway.infrastructure.client.inventoryClient.dto.InventoryProductDto;
import com.inventory.gateway.infrastructure.client.inventoryClient.dto.InventoryResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryOrchestrator {
    private InventoryClient inventoryClient;
    private ProductOrchestrator productOrchestrator;

    public InventoryOrchestrator(InventoryClient inventoryClient, ProductOrchestrator productOrchestrator) {
        this.inventoryClient = inventoryClient;
        this.productOrchestrator = productOrchestrator;
    }

    public InventoryProductDto getInventoryByproduct(Long productId) {
        InventoryProductDto inventoryProduct = new InventoryProductDto();
        ProductDto productDto = new ProductDto();
        boolean existing = productOrchestrator.existsProduct(productId);
        if (existing) {
            inventoryProduct = inventoryClient.getProductInventory(productId);
            productDto = productOrchestrator.getPrductById(productId).getAttributes();

        } else {
            new RuntimeException("Product not found");
        }
        return new InventoryProductDto(
                inventoryProduct.getId(),
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getDescription(),
                inventoryProduct.getQuantity()
        );
    }

    public InventoryResponseWrapper updateQuantity(InventoryRequest inventoryRequest) {
        return inventoryClient.updateQuantity(inventoryRequest);
    }


}
