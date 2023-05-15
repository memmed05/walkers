package com.example.walkers.service.impl;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.profile.GetProfileResponse;
import com.example.walkers.dto.profile.SaveProfileRequest;
import com.example.walkers.dto.profile.SaveProfileResponse;
import com.example.walkers.exception.UserNotFoundException;
import com.example.walkers.mapsturct.ProfileMapstruct;
import com.example.walkers.model.Profile;
import com.example.walkers.model.User;
import com.example.walkers.repository.ProfileRepository;
import com.example.walkers.service.ProfileService;
import com.example.walkers.service.UserService;
import com.example.walkers.util.JwtFilter;
import com.example.walkers.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapstruct profileMapstruct;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    public SaveProfileResponse save(SaveProfileRequest request) {
        User user = userService.getUserByUsername(jwtUtil.getUsernameFromToken());
        Profile profile = profileRepository.save(profileMapstruct.toEntity(request, user));
        return SaveProfileResponse.builder()
                .id(profile.getId())
                .build();
    }

    @Override
    public GetProfileResponse getProfileById(IdRequest request) {
        User user = userService.getUserByUsername(jwtUtil.getUsernameFromToken());
        return profileMapstruct.mapToGet(profileRepository.findByIdAndUser(request.getId(), user)
                .orElseThrow(() -> {
                    log.error("User not found by id: " + request.getId());
                    return new UserNotFoundException("User not found by id");
                }));
    }

    @Override
    public SaveProfileResponse updateProfile(SaveProfileRequest profileRequest, UUID id) {
        User user = userService.getUserByUsername(jwtUtil.getUsernameFromToken());
        Profile profile = profileRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new UserNotFoundException("User not found by id: " + id));
        profile.setName(profileRequest.getName());
        profile.setSurname(profileRequest.getSurname());
        profile.setPatronymic(profileRequest.getPatronymic());
        profile.setBio(profileRequest.getBio());
        profile.setPpUrl(profileRequest.getPpUrl());
        profileRepository.save(profile);
        return SaveProfileResponse.builder()
                .id(profile.getId())
                .build();
    }
}
