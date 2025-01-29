package com.projet.kata.service.impl;

import com.projet.kata.model.dao.AccountDao;
import com.projet.kata.model.dao.CartDao;
import com.projet.kata.model.dao.CartItemDao;
import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.repository.CartItemRepository;
import com.projet.kata.repository.CartRepository;
import com.projet.kata.repository.ProductRepository;
import com.projet.kata.repository.AccountRepository;
import com.projet.kata.service.CartService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    public CartDao getUserCart(String userEmail) {
        AccountDao user = accountRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(CartDao.builder().user(user).items(new ArrayList<>()).build()));
    }

    @Transactional
    public CartDao addProductToCart(String userEmail, Long productId, int quantity) {
        CartDao cart = getUserCart(userEmail);
        ProductDao product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItemDao> existingItem = Optional.empty();
        if (cart.getItems() != null)
            existingItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst();

        if (existingItem.isPresent()) {
            CartItemDao item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItemDao newItem = CartItemDao.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .build();
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    @Transactional
    public CartDao removeProductFromCart(String userEmail, Long productId) {
        CartDao cart = getUserCart(userEmail);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return cartRepository.save(cart);
    }
}
