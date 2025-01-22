package com.projet.kata.model.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonBackReference
    private CartDao cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductDao product;

    private int quantity;
}