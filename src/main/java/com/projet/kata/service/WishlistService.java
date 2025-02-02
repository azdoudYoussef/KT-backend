package com.projet.kata.service;

import com.projet.kata.model.dto.WishlistDto;

public interface WishlistService {

    WishlistDto getUserWishlist(String userEmail);

    WishlistDto addProductToWishlist(String userEmail, Long productId);

    WishlistDto removeProductFromWishlist(String userEmail, Long productId);
}
