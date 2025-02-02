package com.projet.kata.ut.helper;

import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.model.dto.ProductDto;

public class TestHelper {

    public static final ProductDao product1 = ProductDao.builder()
            .id(1L)
            .name("Product 1")
            .price(100.0)
            .description("Description 1")
            .build();

    public static final ProductDao product2 = ProductDao.builder()
            .id(2L)
            .name("Product 2")
            .price(200.0)
            .description("Description 2")
            .build();

    public static final ProductDto productDto1 = ProductDto.builder()
            .name("Product 1")
            .price(100.0)
            .description("Description 1")
            .build();

    public static final ProductDto productDto2 = ProductDto.builder()
            .name("Product 2")
            .price(200.0)
            .description("Description 2")
            .build();
}
