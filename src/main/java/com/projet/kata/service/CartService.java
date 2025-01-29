package com.projet.kata.service;

import com.projet.kata.model.dao.CartDao;

public interface CartService {

    CartDao getUserCart(String userEmail);

    CartDao addProductToCart(String userEmail, Long productId, int quantity);

    CartDao removeProductFromCart(String userEmail, Long productId);

}
