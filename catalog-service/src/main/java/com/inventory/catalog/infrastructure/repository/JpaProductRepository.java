package com.inventory.catalog.infrastructure.repository;

import com.inventory.catalog.domain.model.Product;
import com.inventory.catalog.domain.repository.IProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaProductRepository implements IProductRepository {


    private final SpringDataProductRepository repository;

    public JpaProductRepository(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product){
        return repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }
    
    @Override
    public Optional<Product> findById(Long id){
        return repository.findById(id);
    }
}
