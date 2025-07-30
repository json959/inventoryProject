package com.inventory.gateway.infrastructure.controller;

import com.inventory.gateway.application.service.InventoryOrchestrator;
import com.inventory.gateway.application.service.ProductOrchestrator;
import com.inventory.gateway.application.service.PurchaseOrchestrator;
import com.inventory.gateway.domain.model.InventoryRequest;
import com.inventory.gateway.domain.model.ProductRequest;
import com.inventory.gateway.domain.model.PurchaseRequest;
import com.inventory.gateway.infrastructure.client.catalogClient.dto.ProductDto;
import com.inventory.gateway.infrastructure.client.catalogClient.dto.ProductListResponseWrapper;
import com.inventory.gateway.infrastructure.client.catalogClient.dto.ProductResponseWrapper;
import com.inventory.gateway.infrastructure.client.inventoryClient.dto.InventoryProductDto;
import com.inventory.gateway.infrastructure.client.inventoryClient.dto.InventoryResponseWrapper;
import com.inventory.gateway.infrastructure.client.purchaseClient.dto.PurchaseDto;
import com.inventory.gateway.infrastructure.client.purchaseClient.dto.PurchaseResponseWrapper;
import com.inventory.gateway.sared.JsonApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.inventory.gateway.infrastructure.config.ApiVersion.V1;

@RestController
@RequestMapping(V1 +"/gateway")
public class GatewayController {
    public final PurchaseOrchestrator purchaseOrchestrator;
    public final ProductOrchestrator productOrchestrator;
    public final InventoryOrchestrator inventoryOrchestrator;


    public GatewayController(PurchaseOrchestrator purchaseOrchestrator, ProductOrchestrator productOrchestrator, InventoryOrchestrator inventoryOrchestrator) {
        this.purchaseOrchestrator = purchaseOrchestrator;
        this.productOrchestrator = productOrchestrator;
        this.inventoryOrchestrator = inventoryOrchestrator;
    }

    @GetMapping("/Purchases/{productId}")
    public ResponseEntity<?> getPurchasesProduct(@PathVariable("productId") Long id) {
        List<PurchaseDto> purchases = purchaseOrchestrator.getPurchasesProduct(id);
        return ResponseEntity.ok().body(
                new PurchaseResponseWrapper("All purchase", id, purchases.size(), purchases)
        );
    }

    @PostMapping("/Purchase")
    public ResponseEntity<?> newPurchase(@RequestBody PurchaseRequest purchase) {
        PurchaseResponseWrapper newPurchase = purchaseOrchestrator.newPurchase(purchase);
        return ResponseEntity.ok().body(
                new PurchaseResponseWrapper("New purchase", newPurchase.getId(), newPurchase.getDate(), newPurchase.getAttributes())
        );
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponseWrapper newProduct = productOrchestrator.createProduct(productRequest);
        return ResponseEntity.ok().body(
                new ProductResponseWrapper("New product", newProduct.getId(), newProduct.getAttributes())
        );
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable("productId") Long id) {
        ProductResponseWrapper product = productOrchestrator.getPrductById(id);
        return ResponseEntity.ok().body(
                new ProductResponseWrapper("Product", product.getId(), product.getAttributes())
        );
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDto> product = productOrchestrator.getAllProducts();
        return ResponseEntity.ok().body(
                new JsonApiResponse("All Products", 1L, product)
        );
    }

    @GetMapping("/inventory/{productId}")
    public ResponseEntity<?> getInventoryProduct(@PathVariable("productId") Long id) {
        InventoryProductDto inventoryProduct = inventoryOrchestrator.getInventoryByproduct(id);
        return ResponseEntity.ok().body(
                new JsonApiResponse("Inventory Product", inventoryProduct.getId(), inventoryProduct)
        );
    }

    @PutMapping("/inventory/udpateQuantiy")
    public ResponseEntity<?> updateQuantity(@RequestBody InventoryRequest request){
        InventoryResponseWrapper inventory = inventoryOrchestrator.updateQuantity(request);

        return ResponseEntity.ok().body(
                new InventoryResponseWrapper("inventory updated", inventory.getId(),inventory.getAttributes())
        );
    }




}
