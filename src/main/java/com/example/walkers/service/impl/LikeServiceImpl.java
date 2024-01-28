package com.example.walkers.service.impl;

import com.example.walkers.dto.like.AddLikeRequest;
import com.example.walkers.exception.PostNotFoundException;
import com.example.walkers.model.Like;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import com.example.walkers.repository.LikeRepository;
import com.example.walkers.repository.PostRepository;
import com.example.walkers.service.LikeService;
import com.example.walkers.service.UserService;
import com.example.walkers.util.JwtUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @Override
    public Integer addLike(AddLikeRequest request) {
        Post post = postRepository.findById(request.id()).orElseThrow(() -> {
            log.error("Post not found by id: " + request.id());
            return new PostNotFoundException("Post not found by id");
        });
        User user = userService.getUserByUsernameOrEmail(jwtUtil.getUsernameFromToken());
        Like like = likeRepository.save(AddLikeRequest.converToEnt(user, post));
        Optional<List<Like>> optionalLikes = likeRepository.findLikeByPost(like.getPost());
        List<Like> likes = new ArrayList<>();
        if (optionalLikes.isPresent()) {
            likes = optionalLikes.get();
        }
        return likes.size();
    }

    @Override
    public Boolean deleteLike(UUID id) {
        return likeRepository.deleteLikeByUserUsernameAndPostId(jwtUtil.getUsernameFromToken(), id);
    }
}
