package com.example.walkers.service.impl;

import com.example.walkers.dto.login.LoginRequest;
import com.example.walkers.dto.login.LoginResponse;
import com.example.walkers.security.TokenGenerator;
import com.example.walkers.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.usernameOrEmail(), request.password())
            );
            return new LoginResponse(tokenGenerator.generateToken(authentication));
        } catch (Exception e) {
            throw new BadCredentialsException("Bad Credentials");
        }
    }
}
