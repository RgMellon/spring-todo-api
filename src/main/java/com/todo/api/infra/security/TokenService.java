package com.todo.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.todo.api.domain.user.jpa.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("{api.security.token.secret}")
    private  String secret;

    public String makeToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return  JWT.create()
                    .withIssuer("Api Todo.list")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
       try {
           var algorithm = Algorithm.HMAC256(secret);
           return  JWT.require(algorithm).withIssuer("Api Todo.list").
                   build().verify(tokenJWT)
                   .getSubject();
       }catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado");
       }
    }
}
