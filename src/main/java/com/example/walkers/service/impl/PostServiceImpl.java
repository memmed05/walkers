package com.example.walkers.service.impl;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.IdResponse;
import com.example.walkers.dto.post.GetPostById;
import com.example.walkers.dto.post.PostSaveDto;
import com.example.walkers.dto.post.PostUpdateRequest;
import com.example.walkers.exception.PostNotFoundException;
import com.example.walkers.mapsturct.PostMapstruct;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import com.example.walkers.repository.PostRepository;
import com.example.walkers.service.PostService;
import com.example.walkers.service.UserService;
import com.example.walkers.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapstruct postMapstruct;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    public PostSaveDto savePost(PostSaveDto post) {
        User user = userService.getUserByUsername(jwtUtil.getUsernameFromToken());
        postRepository.save(postMapstruct.toEntity(post, user));
        return post;
    }

    @Override
    public IdResponse deletePost(UUID id) {
        User user = userService.getUserByUsername(jwtUtil.getUsernameFromToken());
        Post post = postRepository.findByIdAndUser(id, user).orElseThrow(() -> {
            log.error("Post not found by id: " + id);
            return new PostNotFoundException("Post not found by id");
        });
        postRepository.deleteById(post.getId());
        return IdResponse.builder()
                .id(post.getId())
                .build();
    }

    @Override
    public PostSaveDto updatePost(PostUpdateRequest postRequest, UUID id) {
        User user = userService.getUserByUsername(jwtUtil.getUsernameFromToken());
        Post post = postRepository.findByIdAndUser(id, user).orElseThrow(() -> {
            log.error("Post not found by id: " + id);
            return new PostNotFoundException("Post not found by id");
        });
        post.setDescription(postRequest.getDescription());

        return postMapstruct.mapToGet(postRepository.save(post));
    }

    @Override
    public GetPostById getPostById(IdRequest request) {
        User user = userService.getUserByUsername(jwtUtil.getUsernameFromToken());
        Post post = postRepository.findByIdAndUser(request.getId(), user).orElseThrow(() -> {
            log.error("Post not found by id: " + request.getId());
            return new PostNotFoundException("Post not found by id");
        });
        return postMapstruct.mapToGetAll(post);
    }
}
