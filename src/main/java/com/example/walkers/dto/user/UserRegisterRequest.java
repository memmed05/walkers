package com.example.walkers.dto.user;

import com.example.walkers.model.Role;
import com.example.walkers.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(
        @Size(min = 3)
        String username,
        @Email(message = "Invalid email format")
        String email,
        @Size(min = 8)
        String password,
        @Size(min = 8)
        String rePassword
) {
    public static User toEntity(UserRegisterRequest request, String password) {
        return new User(
                null,
                null,
                null,
                request.username(),
                request.email(),
                password,
                Role.USER,
                false,
                null,
                null,
                null,
                null
        );
    }
}
