package com.projet.kata.service;

import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.projet.kata.util.Helper.checkIdFormat;

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

        long productId = checkIdFormat(id);

        Optional<ProductDao> productOptional = productRepository.findById(productId);

        return productOptional.orElse(null);

    }

    @Transactional
    public ProductDao updateProduct(String id, ProductDao productDao) {
        long productId = checkIdFormat(id);

        Optional<ProductDao> existingProductOptional = productRepository.findById(productId);

        if (existingProductOptional.isPresent()) {
            ProductDao existingProduct = existingProductOptional.get();

            existingProduct.setCode(productDao.getCode());
            existingProduct.setName(productDao.getName());
            existingProduct.setDescription(productDao.getDescription());
            existingProduct.setImage(productDao.getImage());
            existingProduct.setCategory(productDao.getCategory());
            existingProduct.setPrice(productDao.getPrice());
            existingProduct.setQuantity(productDao.getQuantity());
            existingProduct.setInternalReference(productDao.getInternalReference());
            existingProduct.setShellId(productDao.getShellId());
            existingProduct.setInventoryStatus(productDao.getInventoryStatus());
            existingProduct.setRating(productDao.getRating());
            existingProduct.setUpdatedAt(Instant.now().toEpochMilli());
            try {
                return productRepository.save(existingProduct);
            } catch (Exception e) {
                throw new RuntimeException("Failed to update the product", e);
            }
        }

        return null;
    }

    @Transactional
    public boolean removeProduct(String id) {
        long productId = checkIdFormat(id);

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
