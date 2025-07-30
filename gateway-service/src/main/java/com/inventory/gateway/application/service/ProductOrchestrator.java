package com.inventory.gateway.application.service;

import com.inventory.gateway.domain.model.ProductRequest;
import com.inventory.gateway.infrastructure.client.catalogClient.ProductClient;
import com.inventory.gateway.infrastructure.client.catalogClient.dto.ProductDto;
import com.inventory.gateway.infrastructure.client.catalogClient.dto.ProductResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductOrchestrator {
    private ProductClient productClient;

    public ProductOrchestrator(ProductClient productClient) {
        this.productClient = productClient;
    }

    public ProductResponseWrapper createProduct(ProductRequest productRequest){
        return productClient.createProdict(productRequest);
    }

    public ProductResponseWrapper getPrductById(Long productId){
        return productClient.getProductInfo(productId);
    }

    public List<ProductDto> getAllProducts(){
        return productClient.getAllProducts();
    }

    public boolean existsProduct(Long productId){
        return productClient.existsProduct(productId);
    }
}
