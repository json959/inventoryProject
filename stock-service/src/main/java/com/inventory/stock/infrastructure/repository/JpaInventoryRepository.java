package com.inventory.stock.infrastructure.repository;

import com.inventory.stock.domain.model.Inventory;
import com.inventory.stock.domain.repository.IInventoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaInventoryRepository implements IInventoryRepository {

    private final SpringDataInventoryRepository repository;

    public JpaInventoryRepository(SpringDataInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Inventory save(Inventory inventory){
        return repository.save(inventory);
    }

    @Override
    public Optional<Inventory> findByProductId(Long productId){
        return repository.findByProductId(productId);
    }



}
