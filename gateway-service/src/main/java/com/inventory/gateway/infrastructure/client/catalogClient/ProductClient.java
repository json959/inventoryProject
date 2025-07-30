package com.inventory.gateway.infrastructure.client.catalogClient;


import com.inventory.gateway.domain.model.ProductRequest;
import com.inventory.gateway.infrastructure.client.catalogClient.dto.ProductDto;
import com.inventory.gateway.infrastructure.client.catalogClient.dto.ProductListResponseWrapper;
import com.inventory.gateway.infrastructure.client.catalogClient.dto.ProductResponseWrapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductClient {
    private final RestTemplate restTemplate;

    @Value("${services.catalog.url}")
    private String catalogBaseUrl;

    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "catalogService", fallbackMethod = "fallbackCatalog")
    @Retry(name = "catalogService")
    public boolean existsProduct(Long productId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            restTemplate.exchange(catalogBaseUrl +"/"+ productId,
                    HttpMethod.GET,
                    entity,
                    String.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }


    @CircuitBreaker(name = "catalogService", fallbackMethod = "fallbackCatalog")
    @Retry(name = "catalogService")
    public ProductResponseWrapper getProductInfo(Long productId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<ProductResponseWrapper> response = restTemplate.exchange(catalogBaseUrl +"/"+ productId,
                    HttpMethod.GET,
                    entity,
                    ProductResponseWrapper.class );
            assert response != null;
            return response.getBody();
        }catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }


    @CircuitBreaker(name = "catalogService", fallbackMethod = "fallbackCatalog")
    @Retry(name = "catalogService")
    public List<ProductDto> getAllProducts(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<ProductListResponseWrapper> response = restTemplate.exchange(catalogBaseUrl,
                    HttpMethod.GET,
                    entity,
                    ProductListResponseWrapper.class );
            assert response != null;
            return response.getBody().getAttributes();
        }catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    public List<ProductDto> fallbackCatalog(Throwable ex) {
        System.out.println("Fallback activado: " + ex.getMessage());
        return List.of(); // lista vacía como fallback
    }

    @CircuitBreaker(name = "catalogService", fallbackMethod = "fallbackCatalog")
    @Retry(name = "catalogService")
    public ProductResponseWrapper createProdict(ProductRequest productRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "123456GATEWAYSECRET");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductRequest> entity = new HttpEntity<>(productRequest,headers);

        try {
            ResponseEntity<ProductResponseWrapper> response = restTemplate.exchange(catalogBaseUrl,
                    HttpMethod.POST,
                    entity,
                    ProductResponseWrapper.class);
            assert response != null;
            return  response.getBody();
        }catch (HttpClientErrorException.NotFound e) {
            return null;
        }

    }

}
