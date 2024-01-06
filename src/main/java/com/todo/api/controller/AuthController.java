package com.todo.api.controller;

import com.todo.api.domain.user.dto.DataAuth;
import com.todo.api.domain.user.jpa.User;
import com.todo.api.infra.security.TokenService;
import com.todo.api.infra.security.dto.DataTokenJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAuth data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authToken);

        var tokenJWT = tokenService.makeToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }
}
