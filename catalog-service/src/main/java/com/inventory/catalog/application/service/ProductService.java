package com.inventory.catalog.application.service;

import com.inventory.catalog.domain.model.Product;
import com.inventory.catalog.domain.repository.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final IProductRepository IProductRepository;

    public ProductService(IProductRepository productRepo) {
        this.IProductRepository = productRepo;
    }

    public Product createProduct(Product product) {
        return IProductRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return IProductRepository.findAll();
    }

    public Optional<Product> findById(Long id){
        return IProductRepository.findById(id);
    }
}
