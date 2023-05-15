package com.example.walkers.controller;

import com.example.walkers.dto.user.UserRegisterRequest;
import com.example.walkers.dto.user.UserRegisterResponse;
import com.example.walkers.model.User;
import com.example.walkers.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest userRequest){
        return new ResponseEntity<>(userService.register(userRequest), HttpStatus.CREATED);
    }
}
