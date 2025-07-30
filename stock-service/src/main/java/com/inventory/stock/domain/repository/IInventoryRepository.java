package com.inventory.stock.domain.repository;

import com.inventory.stock.domain.model.Inventory;

import java.util.Optional;

public interface IInventoryRepository {

    Inventory save(Inventory inventory);
    Optional<Inventory> findByProductId(Long productId);
}
