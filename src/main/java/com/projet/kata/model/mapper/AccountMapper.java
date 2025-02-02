package com.projet.kata.model.mapper;

import com.projet.kata.model.dao.AccountDao;
import com.projet.kata.model.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toDTO(AccountDao dao);

    AccountDao toEntity(AccountDto account);
}
