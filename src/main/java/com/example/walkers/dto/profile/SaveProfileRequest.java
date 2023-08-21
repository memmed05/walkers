package com.example.walkers.dto.profile;

import com.example.walkers.model.Profile;
import com.example.walkers.model.User;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record SaveProfileRequest(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String patronymic,
        @Nullable
        String bio,
        @Nullable
        String ppUrl
) {
    public static Profile toEntity(SaveProfileRequest request, User user) {
        return new Profile(
                null,
                null,
                null,
                request.name(),
                request.surname(),
                request.patronymic(),
                request.bio(),
                request.ppUrl(),
                user
        );
    }
}
