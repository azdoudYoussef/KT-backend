package com.projet.kata.service.impl;

import com.projet.kata.model.dao.AccountDao;
import com.projet.kata.repository.AccountRepository;
import com.projet.kata.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountDao saveAccount(AccountDao account) {
        if (account == null || account.getUsername() == null || account.getEmail() == null || account.getPassword() == null) {
            throw new IllegalArgumentException("Username, email and password are required");
        }

        return accountRepository.save(account);
    }
}
