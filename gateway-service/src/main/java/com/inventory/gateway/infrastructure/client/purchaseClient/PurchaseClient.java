package com.inventory.gateway.infrastructure.client.purchaseClient;

import com.inventory.gateway.domain.model.PurchaseRequest;
import com.inventory.gateway.infrastructure.client.catalogClient.dto.ProductDto;
import com.inventory.gateway.infrastructure.client.inventoryClient.dto.InventoryProductDto;
import com.inventory.gateway.infrastructure.client.inventoryClient.dto.InventoryProductResponseWrapper;
import com.inventory.gateway.infrastructure.client.inventoryClient.dto.InventoryResponseWrapper;
import com.inventory.gateway.infrastructure.client.purchaseClient.dto.PurchaseDto;
import com.inventory.gateway.infrastructure.client.purchaseClient.dto.PurchaseResponseWrapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PurchaseClient {
    private final RestTemplate restTemplate;

    @Value("${services.purchase.url}")
    private String purchaseBaseUrl;

    public PurchaseClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "PurchaseService", fallbackMethod = "fallbackPurchase")
    @Retry(name = "PurchaseService")
    public List<PurchaseDto> PurchasesProduct(Long productId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<PurchaseResponseWrapper> response = restTemplate.exchange(
                    purchaseBaseUrl +"/"+ productId,
                    HttpMethod.GET,
                    entity,
                    PurchaseResponseWrapper.class );
            assert response != null;
            return (List<PurchaseDto>) response.getBody().getAttributes();
        }catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    @CircuitBreaker(name = "PurchaseService", fallbackMethod = "fallbackPurchase")
    @Retry(name = "PurchaseService")
    public PurchaseResponseWrapper NewPurchase(PurchaseRequest purchaseRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PurchaseRequest> entity = new HttpEntity<>(purchaseRequest,headers);

        try {
            ResponseEntity<PurchaseResponseWrapper> response = restTemplate.exchange(purchaseBaseUrl,
                    HttpMethod.POST,
                    entity,
                    PurchaseResponseWrapper.class);
            assert response != null;
            return  response.getBody();
        }catch (HttpClientErrorException.NotFound e) {
            return null;
        }

    }
    public List<ProductDto> fallbackPurchase(Throwable ex) {
        System.out.println("Fallback activado: " + ex.getMessage());
        return List.of(); // lista vacía como fallback
    }


}
