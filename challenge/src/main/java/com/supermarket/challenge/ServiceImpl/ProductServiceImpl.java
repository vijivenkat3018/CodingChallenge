package com.supermarket.challenge.ServiceImpl;

import com.supermarket.challenge.Exception.ResourceNotFoundException;
import com.supermarket.challenge.Model.Products;
import com.supermarket.challenge.Service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final RestTemplate restTemplate;
    private final String wiremock;

//    public ProductServiceImpl(RestTemplate restTemplate, String wiremockProductsUrl) {
//        this.restTemplate = restTemplate;
//        this.wiremock = wiremockProductsUrl;
//    }
    public ProductServiceImpl(@Qualifier("wiremock") RestTemplate restTemplate,
                          @Value("${integrations.wiremock.products}") String wiremock) {
        this.restTemplate = restTemplate;
        this.wiremock = wiremock;
    }
     @Override
    public List<Products> getAllProducts() {
       return Arrays.asList(restTemplate.getForEntity(wiremock, Products[].class).getBody());
    }

    @Override
    public Products getProductById(String id) {
        try {
            return Optional.ofNullable(restTemplate.getForEntity(wiremock+ "/" + id, Products.class).getBody())
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        } catch (HttpClientErrorException e) {
            throw new ResourceNotFoundException("product.notfound", e, e.getStatusCode());
        }
    }

    @Override
    public Products save(Products product) {

        return product;
    }


}