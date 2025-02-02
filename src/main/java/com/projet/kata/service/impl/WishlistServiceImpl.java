package com.projet.kata.service.impl;

import com.projet.kata.model.dao.AccountDao;
import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.model.dao.WishlistDao;
import com.projet.kata.model.dao.WishlistItemDao;
import com.projet.kata.model.dto.WishlistDto;
import com.projet.kata.model.mapper.WishlistMapper;
import com.projet.kata.repository.AccountRepository;
import com.projet.kata.repository.ProductRepository;
import com.projet.kata.repository.WishlistItemRepository;
import com.projet.kata.repository.WishlistRepository;
import com.projet.kata.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistItemRepository wishlistItemRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final WishlistMapper wishlistMapper;

    public WishlistDto getUserWishlist(String userEmail) {
        return this.wishlistMapper.toDTO(getWishlistByEmail(userEmail));
    }

    private WishlistDao getWishlistByEmail(String userEmail) {
        AccountDao user = accountRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return wishlistRepository.findByUser(user)
                .orElseGet(() -> wishlistRepository.save(WishlistDao.builder().user(user).items(new ArrayList<>()).build()));
    }

    @Transactional
    public WishlistDto addProductToWishlist(String userEmail, Long productId) {
        WishlistDao wishlist = getWishlistByEmail(userEmail);
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

        return this.wishlistMapper.toDTO(wishlistRepository.save(wishlist));
    }

    @Transactional
    public WishlistDto removeProductFromWishlist(String userEmail, Long productId) {
        WishlistDao wishlist = getWishlistByEmail(userEmail);
        wishlist.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return this.wishlistMapper.toDTO(wishlistRepository.save(wishlist));
    }
}
