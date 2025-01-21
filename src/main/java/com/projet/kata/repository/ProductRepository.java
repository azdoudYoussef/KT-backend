package com.projet.kata.repository;

import com.projet.kata.model.dao.ProductDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDao, Long> {



}
