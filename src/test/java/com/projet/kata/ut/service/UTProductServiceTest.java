package com.projet.kata.ut.service;

import com.projet.kata.KataApplicationTests;
import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.repository.ProductRepository;
import com.projet.kata.service.ProductService;
import com.projet.kata.ut.helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;


public class UTProductServiceTest extends KataApplicationTests {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;



    @Test
    public void shouldReturnProductListWhenProductsExist() {

        List<ProductDao> mockProducts = List.of(TestHelper.product1, TestHelper.product2);

        Mockito.when(productRepository.findAll()).thenReturn(mockProducts);

        List<ProductDao> result = productService.getAllProducts();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Product 1", result.get(0).getName());
        Assertions.assertEquals("Product 2", result.get(1).getName());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();

    }

    @Test
    void shouldReturnEmptyListWhenNoProductsExist() {

        Mockito.when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<ProductDao> result = productService.getAllProducts();

        Assertions.assertTrue(result.isEmpty());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }
}
