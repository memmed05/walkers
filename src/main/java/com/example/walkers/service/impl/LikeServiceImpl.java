package com.example.walkers.service.impl;

import com.example.walkers.dto.like.AddLikeRequest;
import com.example.walkers.dto.like.GetLikesOfPostByIdResponse;
import com.example.walkers.exception.PostNotFoundException;
import com.example.walkers.mapsturct.LikeMapStruct;
import com.example.walkers.model.Comment;
import com.example.walkers.model.Like;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import com.example.walkers.repository.LikeRepository;
import com.example.walkers.repository.PostRepository;
import com.example.walkers.repository.UserRepository;
import com.example.walkers.service.LikeService;
import com.example.walkers.service.UserService;
import com.example.walkers.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final LikeMapStruct likeMapStruct;
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @Override
    public GetLikesOfPostByIdResponse addLike(AddLikeRequest request) {
        Post post = postRepository.findById(request.getId()).orElseThrow(() -> {
            log.error("Post not found by id: " + request.getId());
            return new PostNotFoundException("Post not found by id");
        });
        User user = userService.getUserByUsername(jwtUtil.getUsernameFromToken());
        Like like = likeRepository.save(likeMapStruct.toEntity(post, user));
        Optional<List<Like>> optionalLikes = likeRepository.findLikeByPost(like.getPost());
        List<Like> likes = new ArrayList<>();
        if (optionalLikes.isPresent()) {
            likes = optionalLikes.get();
        }
        return likeMapStruct.mapToLikes(likes);
    }
}
