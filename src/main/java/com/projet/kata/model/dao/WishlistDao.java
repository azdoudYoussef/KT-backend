package com.projet.kata.model.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "wishlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private AccountDao user;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<WishlistItemDao> items;

}
