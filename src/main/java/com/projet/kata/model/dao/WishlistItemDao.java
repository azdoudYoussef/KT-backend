package com.projet.kata.model.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wishlist_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistItemDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", nullable = false)
    @JsonBackReference
    private WishlistDao wishlist;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductDao product;
}
