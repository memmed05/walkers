package com.example.walkers.repository;

import com.example.walkers.model.Profile;
import com.example.walkers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByIdAndUser(UUID id, User user);
}
