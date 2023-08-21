package com.example.walkers.dto.user;

public record UserRegisterResponse(
        String username,
        String email,
        Boolean confirmed
) {
}
