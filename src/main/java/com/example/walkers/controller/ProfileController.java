package com.example.walkers.controller;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.profile.GetProfileResponse;
import com.example.walkers.dto.profile.SaveProfileRequest;
import com.example.walkers.dto.profile.SaveProfileResponse;
import com.example.walkers.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<SaveProfileResponse> saveProfile(@Valid @RequestBody SaveProfileRequest saveProfileRequest) {
        return new ResponseEntity<>(profileService.save(saveProfileRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProfileResponse> getProfileById(@PathVariable UUID id) {
        return ResponseEntity.ok(profileService.getProfileById(new IdRequest(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaveProfileResponse> updateProfile(@Valid @RequestBody SaveProfileRequest saveProfileRequest,
                                                             @PathVariable UUID id) {
        return ResponseEntity.ok(profileService.updateProfile(saveProfileRequest, id));
    }
}
