package com.inventory.catalog.domain.repository;

import com.inventory.catalog.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {

    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
}
