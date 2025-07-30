package com.inventory.gateway.infrastructure.client.inventoryClient;


import com.inventory.gateway.domain.model.InventoryRequest;
import com.inventory.gateway.infrastructure.client.catalogClient.dto.ProductDto;
import com.inventory.gateway.infrastructure.client.inventoryClient.dto.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class InventoryClient {
    private final RestTemplate restTemplate;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${services.inventory.url}")
    private String inventoryBaseUrl;

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackinventory")
    @Retry(name = "invneptryService")
    public InventoryProductDto getProductInventory(Long productId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<InventoryProductResponseWrapper> response = restTemplate.exchange(
                    inventoryBaseUrl +"/"+ productId,
                    HttpMethod.GET,
                    entity,
                    InventoryProductResponseWrapper.class );
            assert response != null;
            return response.getBody().getAttributes();
        }catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackinventory")
    @Retry(name = "invneptryService")
    public InventoryResponseWrapper updateQuantity(InventoryRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<InventoryRequest> requestEntity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<InventoryResponseWrapper> response = restTemplate.exchange("http://localhost:8081/inventory",
                    HttpMethod.PUT,
                    requestEntity,
                    InventoryResponseWrapper.class);
            assert response != null;
            return response.getBody();
        }catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    public List<ProductDto> fallbackinventory(Throwable ex) {
        System.out.println("Fallback activado: " + ex.getMessage());
        return List.of(); // lista vacía como fallback
    }
}
