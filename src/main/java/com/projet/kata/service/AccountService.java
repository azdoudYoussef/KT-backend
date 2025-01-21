package com.projet.kata.service;

import com.projet.kata.model.dao.AccountDao;
import com.projet.kata.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    public AccountDao saveAccount(AccountDao account) {
        if (account == null || account.getUsername() == null || account.getEmail() == null || account.getPassword() == null) {
            throw new IllegalArgumentException("Username, email and password are required");
        }

        return accountRepository.save(account);
    }
}
