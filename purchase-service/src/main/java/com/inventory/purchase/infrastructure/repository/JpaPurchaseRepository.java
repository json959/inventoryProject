package com.inventory.purchase.infrastructure.repository;

import com.inventory.purchase.domain.model.Purchase;
import com.inventory.purchase.domain.repository.IPurchaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaPurchaseRepository implements IPurchaseRepository {
    private final SpringDataPurchaseRepository dataPurchaseRepository;

    public JpaPurchaseRepository(SpringDataPurchaseRepository dataPurchaseRepository) {
        this.dataPurchaseRepository = dataPurchaseRepository;
    }

    @Override
    public Purchase save(Purchase purchase) {
        return dataPurchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> poductPurchases(Long productId) {
        return dataPurchaseRepository.findByProductId(productId);
    }


}
