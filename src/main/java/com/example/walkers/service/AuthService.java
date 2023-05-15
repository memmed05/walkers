package com.example.walkers.service;

import com.example.walkers.dto.login.LoginRequest;
import com.example.walkers.dto.login.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
