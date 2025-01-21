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
}
