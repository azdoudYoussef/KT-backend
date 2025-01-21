package com.projet.kata.service;

import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public ProductDao getProductDetails(String id) {

        long productId;
        try {
            productId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid product ID format");
        }

        Optional<ProductDao> productOptional = productRepository.findById(productId);

        return productOptional.orElse(null);

    }


}
