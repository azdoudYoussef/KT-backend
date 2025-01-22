package com.projet.kata.service;

import com.projet.kata.model.dao.AccountDao;
import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.model.dao.WishlistDao;
import com.projet.kata.model.dao.WishlistItemDao;
import com.projet.kata.repository.AccountRepository;
import com.projet.kata.repository.ProductRepository;
import com.projet.kata.repository.WishlistItemRepository;
import com.projet.kata.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistItemRepository wishlistItemRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    public WishlistDao getUserWishlist(String userEmail) {
        AccountDao user = accountRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return wishlistRepository.findByUser(user)
                .orElseGet(() -> wishlistRepository.save(WishlistDao.builder().user(user).items(new ArrayList<>()).build()));
    }

    @Transactional
    public WishlistDao addProductToWishlist(String userEmail, Long productId) {
        WishlistDao wishlist = getUserWishlist(userEmail);
        ProductDao product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        boolean productExists = wishlist.getItems().stream()
                .anyMatch(item -> item.getProduct().getId().equals(productId));

        if (!productExists) {
            WishlistItemDao newItem = WishlistItemDao.builder()
                    .wishlist(wishlist)
                    .product(product)
                    .build();
            wishlist.getItems().add(wishlistItemRepository.save(newItem));
        }

        return wishlistRepository.save(wishlist);
    }

    @Transactional
    public WishlistDao removeProductFromWishlist(String userEmail, Long productId) {
        WishlistDao wishlist = getUserWishlist(userEmail);
        wishlist.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return wishlistRepository.save(wishlist);
    }
}
