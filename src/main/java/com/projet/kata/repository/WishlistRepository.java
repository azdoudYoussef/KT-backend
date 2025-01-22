package com.projet.kata.repository;

import com.projet.kata.model.dao.AccountDao;
import com.projet.kata.model.dao.WishlistDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<WishlistDao, Long> {
    Optional<WishlistDao> findByUser(AccountDao user);
}
