package com.projet.kata.service.impl;

import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.model.dto.ProductDto;
import com.projet.kata.model.mapper.ProductMapper;
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

    private final ProductMapper productMapper;

    public ProductDto saveProduct(ProductDto product) {
        if (product == null || product.getName() == null || product.getPrice() == null) {
            throw new IllegalArgumentException("Product name and price are required");
        }

        return this.productMapper.toDTO(productRepository.save(this.productMapper.toEntity(product)));
    }

    public List<ProductDto> getAllProducts() {
        return this.productMapper.toDTOList(productRepository.findAll());
    }

    public ProductDto getProductDetails(Long productId) {

        Optional<ProductDao> product = productRepository.findById(productId);
        return this.productMapper.toDTO(product.orElse(null));

    }

    @Transactional
    public ProductDto updateProduct(Long productId, ProductDto productDto) {

        Optional<ProductDao> existingProductOptional = productRepository.findById(productId);

        if (existingProductOptional.isEmpty()) {
            return null;
        }

        boolean productIsUpdated = false;

        ProductDao existingProduct = existingProductOptional.get();

        if (productDto.getCode().isEmpty()) {
            existingProduct.setCode(productDto.getCode());
            productIsUpdated = true;
        }

        if (productDto.getName().isEmpty()) {
            existingProduct.setName(productDto.getName());
            productIsUpdated = true;
        }

        if (productDto.getDescription().isEmpty()) {
            existingProduct.setDescription(productDto.getDescription());
            productIsUpdated = true;
        }

        if (productDto.getImage().isEmpty()) {
            existingProduct.setImage(productDto.getImage());
            productIsUpdated = true;
        }

        if (productDto.getCategory().isEmpty()) {
            existingProduct.setCategory(productDto.getCategory());
            productIsUpdated = true;
        }

        if (!productDto.getPrice().equals(existingProduct.getPrice())) {
            existingProduct.setPrice(productDto.getPrice());
            productIsUpdated = true;
        }

        if (!productDto.getQuantity().equals(existingProduct.getQuantity())) {
            existingProduct.setQuantity(productDto.getQuantity());
            productIsUpdated = true;
        }

        if (productDto.getInternalReference().isEmpty()) {
            existingProduct.setInternalReference(productDto.getInternalReference());
            productIsUpdated = true;
        }

        if (!productDto.getShellId().equals(existingProduct.getShellId())) {
            existingProduct.setShellId(productDto.getShellId());
            productIsUpdated = true;
        }

        if (!productDto.getInventoryStatus().equals(existingProduct.getInventoryStatus())) {
            existingProduct.setInventoryStatus(productDto.getInventoryStatus());
            productIsUpdated = true;
        }

        if (!productDto.getRating().equals(existingProduct.getRating())) {
            existingProduct.setRating(productDto.getRating());
            productIsUpdated = true;
        }

        if (productIsUpdated) {
            existingProduct.setUpdatedAt(Instant.now().toEpochMilli());
        }

        try {
            return this.productMapper.toDTO(productRepository.save(existingProduct));
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
