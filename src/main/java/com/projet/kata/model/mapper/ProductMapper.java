package com.projet.kata.model.mapper;

import com.projet.kata.model.dao.ProductDao;
import com.projet.kata.model.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDao toEntity(ProductDto dto);

    ProductDto toDTO(ProductDao dao);

    List<ProductDto> toDTOList(List<ProductDao> daoList);

    List<ProductDao> toEntityList(List<ProductDto> dtoList);
}
