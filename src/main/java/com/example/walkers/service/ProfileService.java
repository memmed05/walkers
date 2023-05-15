package com.example.walkers.service;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.profile.GetProfileResponse;
import com.example.walkers.dto.profile.SaveProfileRequest;
import com.example.walkers.dto.profile.SaveProfileResponse;

import java.util.UUID;

public interface ProfileService {
    SaveProfileResponse save(SaveProfileRequest saveProfileRequest);

    GetProfileResponse getProfileById(IdRequest request);

    SaveProfileResponse updateProfile(SaveProfileRequest saveProfileRequest, UUID id);
}
