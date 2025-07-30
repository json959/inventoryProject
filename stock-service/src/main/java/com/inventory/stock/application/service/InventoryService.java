package com.inventory.stock.application.service;

import com.inventory.stock.domain.model.Inventory;
import com.inventory.stock.domain.repository.IInventoryRepository;
import com.inventory.stock.infrastructure.client.ProductClient;
import com.inventory.stock.infrastructure.client.dto.ProductDto;
import com.inventory.stock.shared.ProductStockResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class InventoryService {


    private final IInventoryRepository inventoryRepository;

    private  ProductClient productClient;




    public InventoryService(IInventoryRepository inventoryRepository, ProductClient productClient) {
        this.inventoryRepository = inventoryRepository;
        this.productClient = productClient;

    }

    public Inventory createInventory(Inventory inventory){ return inventoryRepository.save(inventory);}

    public Inventory getInventoryByproduct(Long productId){
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("No inventory for product ID " + productId));
    }

    public Inventory updateQunatity(Long productId, Long quantity){
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("No inventory for product ID " + productId));

        inventory.setQuantity(quantity);
        return inventoryRepository.save(inventory);
    }
}
