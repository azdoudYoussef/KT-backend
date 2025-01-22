package com.projet.kata.repository;

import com.projet.kata.model.dao.AccountDao;
import com.projet.kata.model.dao.CartDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartDao, Long> {
    Optional<CartDao> findByUser(AccountDao user);
}

