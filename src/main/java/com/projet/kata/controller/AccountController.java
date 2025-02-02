package com.projet.kata.controller;

import com.projet.kata.model.dto.AccountDto;
import com.projet.kata.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> CreateNewAccount(@RequestBody AccountDto account) {
        AccountDto createdAccount = accountService.saveAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

}


