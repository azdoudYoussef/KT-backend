package com.projet.kata.repository;

import com.projet.kata.model.dao.AccountDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountDao, Long> {

    Optional<AccountDao> findByEmail(String email);
}
