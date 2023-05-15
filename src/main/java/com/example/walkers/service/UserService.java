package com.example.walkers.service;

import com.example.walkers.dto.user.UserRegisterRequest;
import com.example.walkers.dto.user.UserRegisterResponse;
import com.example.walkers.model.User;

public interface UserService {
    UserRegisterResponse register(UserRegisterRequest userRequest);

    User getUserByUsername(String username);
}
