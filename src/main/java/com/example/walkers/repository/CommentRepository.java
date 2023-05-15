package com.example.walkers.repository;

import com.example.walkers.model.Comment;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    Optional<List<Comment>> findCommentByPost(Post post);

    Optional<Comment> findByIdAndUser(UUID id, User user);
}
