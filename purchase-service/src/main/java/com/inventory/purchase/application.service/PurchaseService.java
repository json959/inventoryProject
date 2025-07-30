package com.inventory.purchase.application.service;

import com.inventory.purchase.domain.model.Purchase;
import com.inventory.purchase.domain.repository.IPurchaseRepository;
import com.inventory.purchase.infrastructure.client.inventoryClient.InventoryClient;
import com.inventory.purchase.infrastructure.client.inventoryClient.dto.InventoryRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.time.LocalTime.now;

@Slf4j
@Service
public class PurchaseService {

    private final IPurchaseRepository iPurchaseRepository;
    private InventoryClient inventoryClient;

    public PurchaseService(IPurchaseRepository iPurchaseRepository, InventoryClient inventoryClient) {
        this.iPurchaseRepository = iPurchaseRepository;
        this.inventoryClient = inventoryClient;
    }

    public Purchase newPurchase(Purchase purchase){
        return iPurchaseRepository.save(purchase);
    }

    public List<Purchase> getProductPurchases(Long productId){
        return iPurchaseRepository.poductPurchases(productId);
    }

}
