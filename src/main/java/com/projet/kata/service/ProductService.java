package com.projet.kata.service;

import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public ProductDao saveProduct(ProductDao product) {
        if (product == null || product.getName() == null || product.getPrice() == null) {
            throw new IllegalArgumentException("Product name and price are required");
        }

        return productRepository.save(product);
    }

    public List<ProductDao> getAllProducts() {
        return productRepository.findAll();
    }


}
