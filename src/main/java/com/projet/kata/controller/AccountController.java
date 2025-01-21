package com.projet.kata.controller;

import com.projet.kata.model.dao.AccountDao;
import com.projet.kata.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDao> CreateNewAccount(@RequestBody AccountDao account) {
        try {
            AccountDao createdAccount = accountService.saveAccount(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}


