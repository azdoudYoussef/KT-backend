package com.projet.kata.model.mapper;

import com.projet.kata.model.dao.WishlistDao;
import com.projet.kata.model.dao.WishlistItemDao;
import com.projet.kata.model.dto.WishlistDto;
import com.projet.kata.model.dto.WishlistItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "items", source = "items")
    WishlistDto toDTO(WishlistDao dao);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "items", target = "items")
    WishlistDao toEntity(WishlistDto dto);

    @Mapping(source = "product.id", target = "productId")
    WishlistItemDto toDTO(WishlistItemDao dao);

    @Mapping(source = "productId", target = "product.id")
    WishlistItemDao toEntity(WishlistItemDto dto);

}
