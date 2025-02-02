package com.projet.kata.model.mapper;

import com.projet.kata.model.dao.CartDao;
import com.projet.kata.model.dao.CartItemDao;
import com.projet.kata.model.dto.CartDto;
import com.projet.kata.model.dto.CartItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "items", source = "items")
    CartDto toDTO(CartDao dao);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "items", target = "items")
    CartDao toEntity(CartDto dto);

    @Mapping(source = "product.id", target = "productId")
    CartItemDto toDTO(CartItemDao cartItem);

    @Mapping(source = "productId", target = "product.id")
    CartItemDao toEntity(CartItemDto cartItemDto);

}
