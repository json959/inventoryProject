package com.inventory.purchase.domain.repository;

import com.inventory.purchase.domain.model.Purchase;

import java.util.List;

public interface IPurchaseRepository {
    Purchase save(Purchase purchase);
    List<Purchase> poductPurchases(Long productId);
}
