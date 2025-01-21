package com.projet.kata.controller;

import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDao> CreateNewProduct(@RequestBody ProductDao product) {
        try {
            ProductDao createdProduct = productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDao>> retrieveAllProducts() {
        try {
            List<ProductDao> products = productService.getAllProducts();
            if (products.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDao> RetrieveProductDetails(@PathVariable(name = "id") String id) {
        try {
            ProductDao product = productService.getProductDetails(id);
            if (product == null)
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDao> updateProductDetails(@PathVariable(name = "id") String id, @RequestBody ProductDao productDao) {
        try {
            ProductDao updatedProduct = productService.updateProduct(id, productDao);
            if (updatedProduct == null)
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
