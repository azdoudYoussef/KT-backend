package com.projet.kata.repository;

import com.projet.kata.model.dao.WishlistItemDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistItemRepository extends JpaRepository<WishlistItemDao, Long> {
}
