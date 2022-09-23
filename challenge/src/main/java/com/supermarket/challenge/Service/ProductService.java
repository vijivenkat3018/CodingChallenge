package com.supermarket.challenge.Service;


import com.supermarket.challenge.Model.Products;

import java.util.List;

public interface ProductService {
    public List<Products> getAllProducts();
    Products getProductById(String id);
    Products save(Products product);

    }




