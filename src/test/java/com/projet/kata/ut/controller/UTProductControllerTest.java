package com.projet.kata.ut.controller;

import com.projet.kata.KataApplicationTests;
import com.projet.kata.controller.ProductController;
import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.service.ProductService;
import com.projet.kata.ut.helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UTProductControllerTest extends KataApplicationTests {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Test
    void shouldReturnOkWhenProductsExist() {

        List<ProductDao> mockProducts = List.of(TestHelper.product1, TestHelper.product2);
        Mockito.when(productService.getAllProducts()).thenReturn(mockProducts);

        ResponseEntity<List<ProductDao>> response = productController.retrieveAllProducts();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        Assertions.assertEquals("Product 1", response.getBody().get(0).getName());
        Mockito.verify(productService, Mockito.times(1)).getAllProducts();
    }

    @Test
    void shouldReturnNoContentWhenNoProductsExist() {

        Mockito.when(productService.getAllProducts()).thenReturn(Collections.emptyList());

        ResponseEntity<List<ProductDao>> response = productController.retrieveAllProducts();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        Mockito.verify(productService, Mockito.times(1)).getAllProducts();
    }

    @Test
    void shouldReturnInternalServerErrorWhenExceptionOccurs() {

        Mockito.when(productService.getAllProducts()).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<List<ProductDao>> response = productController.retrieveAllProducts();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Mockito.verify(productService, Mockito.times(1)).getAllProducts();
    }

}
