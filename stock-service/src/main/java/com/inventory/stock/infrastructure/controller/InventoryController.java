package com.inventory.stock.infrastructure.controller;

import com.inventory.stock.application.service.InventoryService;
import com.inventory.stock.domain.model.Inventory;
import com.inventory.stock.shared.JsonApiResponse;
import com.inventory.stock.shared.ProductStockResponse;
import org.springframework.boot.actuate.autoconfigure.metrics.export.dynatrace.DynatraceProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.inventory.stock.infrastructure.config.ApiVersion.V1;

@RestController
@RequestMapping(V1 +"/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<?> createInventory(@RequestBody Inventory inventory){
        Inventory inventorySaved = inventoryService.createInventory(inventory);
        return ResponseEntity.ok().body(
                new JsonApiResponse("Inventory", inventory.getId(),inventory)
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable("productId") Long id){
        Inventory inventory = inventoryService.getInventoryByproduct(id);

        return ResponseEntity.ok().body(
                new JsonApiResponse("Inventory", inventory.getProductId(), inventory)
        );

    }

    @PutMapping
    public ResponseEntity<?> updateQuantity(@RequestBody Inventory request){
        Inventory inventory = inventoryService.updateQunatity(request.getProductId(),request.getQuantity());

        return ResponseEntity.ok().body(
                new JsonApiResponse("inventory updated", inventory.getId(),inventory)
        );
    }
}
