package com.example.walkers.repository;

import com.example.walkers.model.Like;
import com.example.walkers.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {

    Optional<List<Like>> findLikeByPost(Post post);

    Boolean deleteLikeByUserUsernameAndPostId(String username, UUID uuid);
}
