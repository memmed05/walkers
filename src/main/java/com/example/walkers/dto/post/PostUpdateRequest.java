package com.example.walkers.dto.post;

import jakarta.validation.constraints.NotBlank;

public record PostUpdateRequest(
        @NotBlank
        String description
) {
}
