package com.projet.kata.service;

import com.projet.kata.model.dao.ProductDao;

import java.util.List;

public interface ProductService {

    List<ProductDao> getAllProducts();

    ProductDao getProductDetails(Long id);

    ProductDao saveProduct(ProductDao product);

    ProductDao updateProduct(Long id, ProductDao productDao);

    boolean removeProduct(Long id);
}
