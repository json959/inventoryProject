package com.inventory.catalog.infrastructure.repository;

import com.inventory.catalog.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<Product, Long> {
}
