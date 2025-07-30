package com.inventory.purchase.infrastructure.repository;

import com.inventory.purchase.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataPurchaseRepository extends JpaRepository<Purchase,Long> {
    List<Purchase>  findByProductId(Long productId);
}
