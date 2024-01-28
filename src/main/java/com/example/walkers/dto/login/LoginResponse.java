package com.example.walkers.dto.login;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {

}
