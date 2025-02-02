package com.projet.kata.service;

import com.projet.kata.model.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProductDetails(Long id);

    ProductDto saveProduct(ProductDto product);

    ProductDto updateProduct(Long id, ProductDto productDto);

    boolean removeProduct(Long id);
}
