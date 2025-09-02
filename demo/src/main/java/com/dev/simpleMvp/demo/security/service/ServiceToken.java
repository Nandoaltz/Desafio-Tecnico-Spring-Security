package com.dev.simpleMvp.demo.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dev.simpleMvp.demo.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ServiceToken {

    @Value("${jwt.security.pv.key}")
    private String pvKey;

    public String generationToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(pvKey);
            String tokenJwt = JWT.create()
                    .withIssuer("demo")
                    .withSubject(user.getEmail())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(3000))
                    .sign(algorithm);
            return tokenJwt;
        }catch (JwtException e){
            throw  new RuntimeException("Erro ao gerar token: " + e.getMessage());

        }
    }
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(pvKey);
            return JWT.require(algorithm)
                    .withIssuer("demo").build().verify(token).getSubject();
        }catch (JWTVerificationException e){
            return null;
        }
    }
}
