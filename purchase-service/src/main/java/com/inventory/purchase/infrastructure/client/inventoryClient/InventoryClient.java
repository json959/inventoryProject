package com.inventory.purchase.infrastructure.client.inventoryClient;

import com.inventory.purchase.infrastructure.client.inventoryClient.dto.*;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {
    private final RestTemplate restTemplate;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public InventoryProductDto getProductInventory(Long productId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<InventoryProductResponseWrapper> response = restTemplate.exchange(
                    "http://localhost:8081/inventory/" + productId,
                    HttpMethod.GET,
                    entity,
                    InventoryProductResponseWrapper.class );
            assert response != null;
            return response.getBody().getAttributes();
        }catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    public InventoryDto updateQuantity(InventoryRequestDTO requestDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<InventoryRequestDTO> requestEntity = new HttpEntity<>(requestDTO, headers);

        try {
            ResponseEntity<InventoryResponseWrapper> response = restTemplate.exchange("http://localhost:8081/inventory",
                    HttpMethod.PUT,
                    requestEntity,
                    InventoryResponseWrapper.class);
            assert response != null;
            return response.getBody().getAttributes();
        }catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }
}
