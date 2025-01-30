package com.projet.kata.service.impl;

import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.repository.ProductRepository;
import com.projet.kata.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductDao saveProduct(ProductDao product) {
        if (product == null || product.getName() == null || product.getPrice() == null) {
            throw new IllegalArgumentException("Product name and price are required");
        }

        return productRepository.save(product);
    }

    public List<ProductDao> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductDao getProductDetails(Long productId) {

        Optional<ProductDao> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null);

    }

    @Transactional
    public ProductDao updateProduct(Long productId, ProductDao productDao) {

        Optional<ProductDao> existingProductOptional = productRepository.findById(productId);

        if (existingProductOptional.isEmpty()) {
            return null;
        }

        boolean productIsUpdated = false;

        ProductDao existingProduct = existingProductOptional.get();

        if (productDao.getCode().isEmpty()) {
            existingProduct.setCode(productDao.getCode());
            productIsUpdated = true;
        }

        if (productDao.getName().isEmpty()) {
            existingProduct.setName(productDao.getName());
            productIsUpdated = true;
        }

        if (productDao.getDescription().isEmpty()) {
            existingProduct.setDescription(productDao.getDescription());
            productIsUpdated = true;
        }

        if (productDao.getImage().isEmpty()) {
            existingProduct.setImage(productDao.getImage());
            productIsUpdated = true;
        }

        if (productDao.getCategory().isEmpty()) {
            existingProduct.setCategory(productDao.getCategory());
            productIsUpdated = true;
        }

        if (!productDao.getPrice().equals(existingProduct.getPrice())) {
            existingProduct.setPrice(productDao.getPrice());
            productIsUpdated = true;
        }

        if (!productDao.getQuantity().equals(existingProduct.getQuantity())) {
            existingProduct.setQuantity(productDao.getQuantity());
            productIsUpdated = true;
        }

        if (productDao.getInternalReference().isEmpty()) {
            existingProduct.setInternalReference(productDao.getInternalReference());
            productIsUpdated = true;
        }

        if (!productDao.getShellId().equals(existingProduct.getShellId())) {
            existingProduct.setShellId(productDao.getShellId());
            productIsUpdated = true;
        }

        if (!productDao.getInventoryStatus().equals(existingProduct.getInventoryStatus())) {
            existingProduct.setInventoryStatus(productDao.getInventoryStatus());
            productIsUpdated = true;
        }

        if (!productDao.getRating().equals(existingProduct.getRating())) {
            existingProduct.setRating(productDao.getRating());
            productIsUpdated = true;
        }

        if (productIsUpdated) {
            existingProduct.setUpdatedAt(Instant.now().toEpochMilli());
        }

        try {
            return productRepository.save(existingProduct);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update the product", e);
        }

    }

    @Transactional
    public boolean removeProduct(Long productId) {

        if (productRepository.existsById(productId)) {
            try {
                productRepository.deleteById(productId);
                return true;
            } catch (Exception e) {
                throw new RuntimeException("Failed to delete the product", e);
            }
        }

        return false;
    }

}
