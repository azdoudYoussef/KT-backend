package com.projet.kata.controller;

import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.projet.kata.util.Helper.ensureAdminAccess;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDao> CreateNewProduct(@RequestBody ProductDao product) {
        try {
            ensureAdminAccess();
            ProductDao createdProduct = productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
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
    public ResponseEntity<ProductDao> RetrieveProductDetails(@PathVariable(name = "id") Long id) {
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
    public ResponseEntity<ProductDao> updateProductDetails(@PathVariable(name = "id") Long id, @RequestBody ProductDao productDao) {
        try {
            ensureAdminAccess();
            ProductDao updatedProduct = productService.updateProduct(id, productDao);
            if (updatedProduct == null)
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable(name = "id") Long id) {
        try {
            ensureAdminAccess();
            boolean isRemoved = productService.removeProduct(id);

            if (isRemoved) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
