package com.projet.kata.controller;

import com.projet.kata.model.dao.LoginDao;
import com.projet.kata.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping
    public String login(@RequestBody LoginDao loginDao) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDao.getEmail(), loginDao.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user.getUsername());
    }
}



