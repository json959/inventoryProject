package com.inventory.purchase.infrastructure.controller;

import com.inventory.purchase.application.service.PurchaseService;
import com.inventory.purchase.domain.model.Purchase;
import com.inventory.purchase.shared.JsonApiResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.inventory.purchase.infrastructure.config.ApiVersion.V1;

@RestController
@RequestMapping(V1 +"/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<?> newPurchase(@RequestBody Purchase purchase){
        Purchase newPurchase = purchaseService.newPurchase(purchase);
        return ResponseEntity.ok().body(
                new JsonApiResponse("New purchase",newPurchase.getProductId(),newPurchase.getDatePurchase(),newPurchase)
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> productPurchases(@PathVariable("productId") Long id){
        List<Purchase> purchases = purchaseService.getProductPurchases(id);
        return ResponseEntity.ok().body(
                new JsonApiResponse("All purchase",id,purchases.size(),purchases)
        );
    }
}
