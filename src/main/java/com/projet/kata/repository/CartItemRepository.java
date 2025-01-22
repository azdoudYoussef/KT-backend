package com.projet.kata.repository;

import com.projet.kata.model.dao.CartItemDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemDao, Long> {
}

