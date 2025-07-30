package com.inventory.stock.infrastructure.client;

import com.inventory.stock.infrastructure.client.dto.ProductDto;
import com.inventory.stock.infrastructure.client.dto.ProductResponseWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {
    private final RestTemplate restTemplate;

    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existsProduct(Long productId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            restTemplate.exchange("http://localhost:8080/products/" + productId,
                    HttpMethod.GET,
                    entity,
                    String.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }

    public ProductDto getProductInfo(Long productId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<ProductResponseWrapper> response = restTemplate.exchange("http://localhost:8080/products/" + productId,
                    HttpMethod.GET,
                    entity,
                    ProductResponseWrapper.class );
            assert response != null;
            return response.getBody().getAttributes();
        }catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

}
