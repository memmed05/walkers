package com.example.walkers.dto.login;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank
        String usernameOrEmail,
        @NotBlank
        String password
) {
}
