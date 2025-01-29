package com.projet.kata.service;

import com.projet.kata.model.dao.ProductDao;

import java.util.List;

public interface ProductService {

    List<ProductDao> getAllProducts();

    ProductDao getProductDetails(String id);

    ProductDao saveProduct(ProductDao product);

    ProductDao updateProduct(String id, ProductDao productDao);

    boolean removeProduct(String id);
}
