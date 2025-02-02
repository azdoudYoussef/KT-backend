package com.projet.kata.service.impl;

import com.projet.kata.model.dto.AccountDto;
import com.projet.kata.model.mapper.AccountMapper;
import com.projet.kata.repository.AccountRepository;
import com.projet.kata.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    public AccountDto saveAccount(AccountDto account) {
        if (account == null || account.getUsername() == null || account.getEmail() == null || account.getPassword() == null) {
            throw new IllegalArgumentException("Username, email and password are required");
        }

        return this.accountMapper.toDTO(accountRepository.save(this.accountMapper.toEntity(account)));
    }
}
