package com.example.walkers.service.impl;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.comment.GetCommentsOfPostByIdResponse;
import com.example.walkers.dto.comment.SaveCommentRequest;
import com.example.walkers.exception.PostNotFoundException;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import com.example.walkers.repository.CommentRepository;
import com.example.walkers.repository.PostRepository;
import com.example.walkers.service.CommentService;
import com.example.walkers.service.UserService;
import com.example.walkers.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    public List<GetCommentsOfPostByIdResponse> getCommentsOfPostById(IdRequest request) {
        Post post = postRepository.findById(request.id()).orElseThrow(() -> {
            log.error("Post not found by id: " + request.id());
            return new PostNotFoundException("Post not found by id");
        });
        return post.getComments().stream()
                .map(GetCommentsOfPostByIdResponse::convertToGetResponse)
                .toList();
    }

    @Override
    public UUID addComment(UUID id, SaveCommentRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            log.error("Post not found by id: " + id);
            return new PostNotFoundException("Post not found by id");
        });
        User user = userService.getUserByUsernameOrEmail(jwtUtil.getUsernameFromToken());
        return commentRepository.save(SaveCommentRequest.convertToEnt(request, post, user)).getId();
    }

    @Override
    public Boolean deleteComment(IdRequest request) {
        return commentRepository.deleteCommentByIdAndUserUsername(request.id(), jwtUtil.getUsernameFromToken());
    }
}
