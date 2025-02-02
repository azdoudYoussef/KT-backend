package com.projet.kata.service;

import com.projet.kata.model.dto.CartDto;

public interface CartService {

    CartDto getUserCart(String userEmail);

    CartDto addProductToCart(String userEmail, Long productId, int quantity);

    CartDto removeProductFromCart(String userEmail, Long productId);

}
