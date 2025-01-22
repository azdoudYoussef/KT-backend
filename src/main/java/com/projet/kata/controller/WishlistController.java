package com.projet.kata.controller;

import com.projet.kata.model.dao.WishlistDao;
import com.projet.kata.service.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping
    public ResponseEntity<WishlistDao> getWishlist() {
        String userEmail = getCurrentUserEmail();
        return ResponseEntity.ok(wishlistService.getUserWishlist(userEmail));
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<WishlistDao> addProductToWishlist(@PathVariable Long productId) {
        String userEmail = getCurrentUserEmail();
        return ResponseEntity.ok(wishlistService.addProductToWishlist(userEmail, productId));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<WishlistDao> removeProductFromWishlist(@PathVariable Long productId) {
        String userEmail = getCurrentUserEmail();
        return ResponseEntity.ok(wishlistService.removeProductFromWishlist(userEmail, productId));
    }

}
