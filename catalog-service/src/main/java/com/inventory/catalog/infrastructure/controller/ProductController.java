package com.inventory.catalog.infrastructure.controller;

import com.inventory.catalog.application.service.ProductService;
import com.inventory.catalog.domain.model.Product;
import com.inventory.catalog.shared.JsonApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.inventory.catalog.infrastructure.config.ApiVersion.V1;

@RestController
@RequestMapping(V1 +"/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        Product saved = productService.createProduct(product);

        return ResponseEntity.ok().body(
                new JsonApiResponse("Product", saved.getId(), saved)
        );
    }

    @GetMapping
    public ResponseEntity<?> getProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok().body(
          new JsonApiResponse("Products", products.size(), products )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id){
        Optional<Product> product = productService.findById(id);

        return ResponseEntity.ok().body(
                new JsonApiResponse("Product", product.get().getId(), product)
        );

    }
}
