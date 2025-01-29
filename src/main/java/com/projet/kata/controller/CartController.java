package com.projet.kata.controller;

import com.projet.kata.model.dao.CartDao;
import com.projet.kata.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping
    public ResponseEntity<CartDao> getCart() {
        String userEmail = getCurrentUserEmail();
        return ResponseEntity.ok(cartService.getUserCart(userEmail));
    }

    @PostMapping("/add/{productId}/{quantity}")
    public ResponseEntity<CartDao> addProductToCart(@PathVariable Long productId, @PathVariable int quantity) {
        String userEmail = getCurrentUserEmail();
        return ResponseEntity.ok(cartService.addProductToCart(userEmail, productId, quantity));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<CartDao> removeProductFromCart(@PathVariable Long productId) {
        String userEmail = getCurrentUserEmail();
        return ResponseEntity.ok(cartService.removeProductFromCart(userEmail, productId));
    }
}

