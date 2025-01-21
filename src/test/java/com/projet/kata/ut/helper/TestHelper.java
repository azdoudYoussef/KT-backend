package com.projet.kata.ut.helper;

import com.projet.kata.model.dao.ProductDao;

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
}
