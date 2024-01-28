package com.example.walkers.service.impl;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.post.PostSaveResponse;
import com.example.walkers.dto.post.GetPostById;
import com.example.walkers.dto.post.PostSaveDto;
import com.example.walkers.dto.post.PostUpdateRequest;
import com.example.walkers.exception.PostNotFoundException;
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
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    public PostSaveResponse savePost(PostSaveDto post) {
        User user = userService.getUserByUsernameOrEmail(jwtUtil.getUsernameFromToken());
        return PostSaveResponse.map(postRepository.save(PostSaveDto.toEntity(post, user)));
    }

    @Override
    public Boolean deletePost(UUID id) {
        User user = userService.getUserByUsernameOrEmail(jwtUtil.getUsernameFromToken());
        Post post = postRepository.findByIdAndUser(id, user).orElseThrow(() -> {
            log.error("Post not found by id: " + id);
            return new PostNotFoundException("Post not found by id");
        });
        postRepository.deleteById(post.getId());
        return true;
    }

    @Override
    public PostSaveResponse updatePost(PostUpdateRequest postRequest, UUID id) {
        User user = userService.getUserByUsernameOrEmail(jwtUtil.getUsernameFromToken());
        Post post = postRepository.findByIdAndUser(id, user).orElseThrow(() -> {
            log.error("Post not found by id: " + id);
            return new PostNotFoundException("Post not found by id");
        });
        post.setDescription(postRequest.description());

        return PostSaveResponse.map(post);
    }

    @Override
    public GetPostById getPostById(IdRequest request) {
        User user = userService.getUserByUsernameOrEmail(jwtUtil.getUsernameFromToken());
        Post post = postRepository.findByIdAndUser(request.id(), user).orElseThrow(() -> {
            log.error("Post not found by id: " + request.id());
            return new PostNotFoundException("Post not found by id");
        });
        return GetPostById.getPost(post);
    }
}
