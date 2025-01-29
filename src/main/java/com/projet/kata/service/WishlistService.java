package com.projet.kata.service;

import com.projet.kata.model.dao.WishlistDao;

public interface WishlistService {

    WishlistDao getUserWishlist(String userEmail);

    WishlistDao addProductToWishlist(String userEmail, Long productId);

    WishlistDao removeProductFromWishlist(String userEmail, Long productId);
}
