package com.example.walkers.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {

    @Size(min = 3)
    private String username;
    @Email(message = "Invalid email format")
    private String email;
    @Size(min = 8)
    private String password;
    @Size(min = 8)
    private String RePassword;
}
