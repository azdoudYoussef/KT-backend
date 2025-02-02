package com.projet.kata.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistDto {

    private Long id;

    private Long userId;

    private List<WishlistItemDto> items;

}
