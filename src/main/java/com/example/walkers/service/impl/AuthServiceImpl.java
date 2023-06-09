package com.example.walkers.service.impl;

import com.example.walkers.dto.login.LoginRequest;
import com.example.walkers.dto.login.LoginResponse;
import com.example.walkers.service.AuthService;
import com.example.walkers.service.UserService;
import com.example.walkers.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            return LoginResponse.builder()
                    .token(tokenGenerator.generateToken(authentication))
                    .build();
        } catch (Exception e) {
            throw new BadCredentialsException("Bad Credentials");
        }
    }
}
