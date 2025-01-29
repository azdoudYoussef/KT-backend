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
        ensureAdminAccess();
        ProductDao createdProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductDao>> retrieveAllProducts() {
        List<ProductDao> products = productService.getAllProducts();
        if (products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDao> RetrieveProductDetails(@PathVariable(name = "id") Long id) {
        ProductDao product = productService.getProductDetails(id);
        if (product == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDao> updateProductDetails(@PathVariable(name = "id") Long id, @RequestBody ProductDao productDao) {
        ensureAdminAccess();
        ProductDao updatedProduct = productService.updateProduct(id, productDao);
        if (updatedProduct == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedProduct);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable(name = "id") Long id) {
        ensureAdminAccess();
        boolean isRemoved = productService.removeProduct(id);

        if (isRemoved) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
