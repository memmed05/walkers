package com.example.walkers.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.walkers.exception.InvalidTokenException;
import com.example.walkers.model.User;
import com.example.walkers.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenGenerator {

    private final UserService userService;

    @Value("${jwt-variables.KEY}")
    private String KEY;

    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;

    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private int EXPIRES_ACCESS_TOKEN_MINUTE;

    @Value("${jwt-variables.EXPIRES_REFRESH_TOKEN_MINUTE}")
    private int EXPIRES_REFRESH_TOKEN_MINUTE;

    public String generateToken(Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.getUserByUsernameOrEmail(username);
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis()
                        + (EXPIRES_ACCESS_TOKEN_MINUTE * 60 * 1000)))
                .withIssuer(ISSUER)
                .withClaim("email", user.getEmail())
                .sign(Algorithm.HMAC256(KEY.getBytes()));
    }

    public String generateRefreshToke(Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.getUserByUsernameOrEmail(username);
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis()
                        + (EXPIRES_REFRESH_TOKEN_MINUTE * 60 * 1000)))
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(KEY.getBytes()));
    }

    public DecodedJWT verifyJWT(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(KEY.getBytes())).build();
        try {
            return jwtVerifier.verify(token);
        } catch (Exception e) {
            log.error("Invalid token: " + token);
            throw new InvalidTokenException("Invalid token");
        }
    }
}
