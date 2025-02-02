package com.projet.kata.controller;

import com.projet.kata.model.dto.ProductDto;
import com.projet.kata.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "${swagger.controller.createNewProduct.value}",
            description = "${swagger.controller.createNewProduct.notes}")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createNewProduct(
            @Parameter(description = "${swagger.controller.createNewProduct.param.product}")
            @RequestBody ProductDto product) {
        ProductDto createdProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @Operation(summary = "${swagger.controller.retrieveAllProducts.value}",
            description = "${swagger.controller.retrieveAllProducts.notes}")
    @GetMapping
    public ResponseEntity<List<ProductDto>> retrieveAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        if (products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "${swagger.controller.retrieveProductDetails.value}",
            description = "${swagger.controller.retrieveProductDetails.notes}")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> retrieveProductDetails(
            @Parameter(description = "${swagger.controller.retrieveProductDetails.param.id}")
            @PathVariable(name = "id") Long id) {
        ProductDto product = productService.getProductDetails(id);
        if (product == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(product);
    }

    @Operation(summary = "${swagger.controller.updateProductDetails.value}",
            description = "${swagger.controller.updateProductDetails.notes}")
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> updateProductDetails(
            @Parameter(description = "${swagger.controller.updateProductDetails.param.id}")
            @PathVariable(name = "id") Long id,
            @Parameter(description = "${swagger.controller.updateProductDetails.param.product}")
            @RequestBody ProductDto product) {
        ProductDto updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedProduct);

    }

    @Operation(summary = "${swagger.controller.removeProduct.value}",
            description = "${swagger.controller.removeProduct.notes}")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removeProduct(
            @Parameter(description = "${swagger.controller.removeProduct.param.id}")
            @PathVariable(name = "id") Long id) {
        boolean isRemoved = productService.removeProduct(id);

        if (isRemoved) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
