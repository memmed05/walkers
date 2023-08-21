package com.example.walkers.dto.profile;

import com.example.walkers.model.Profile;

public record GetProfileResponse(
        String name,
        String surname,
        String patronymic,
        String bio,
        String ppUrl
) {
    public static GetProfileResponse mapToGet(Profile profile) {
        return new GetProfileResponse(
                profile.getName(),
                profile.getSurname(),
                profile.getPatronymic(),
                profile.getBio(),
                profile.getPpUrl()
        );
    }
}
