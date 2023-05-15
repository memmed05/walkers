package com.example.walkers.repository;

import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    Optional<Post> findByIdAndUser(UUID id, User user);
}
