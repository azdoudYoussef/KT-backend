package com.projet.kata.service;

import com.projet.kata.model.dao.AccountDao;

public interface AccountService {

    AccountDao saveAccount(AccountDao account);

}
