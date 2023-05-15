package com.example.walkers.service.impl;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.IdResponse;
import com.example.walkers.dto.comment.GetCommentsOfPostByIdRequest;
import com.example.walkers.dto.comment.GetCommentsOfPostByIdResponse;
import com.example.walkers.dto.comment.SaveCommentRequest;
import com.example.walkers.exception.CommentNotFoundException;
import com.example.walkers.exception.PostNotFoundException;
import com.example.walkers.mapsturct.CommentMapstruct;
import com.example.walkers.model.Comment;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapstruct commentMapstruct;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    public List<GetCommentsOfPostByIdResponse> getCommentsOfPostById(GetCommentsOfPostByIdRequest request) {
        return null;
    }

    @Override
    public List<GetCommentsOfPostByIdResponse> addComment(UUID id, SaveCommentRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            log.error("Post not found by id: " + id);
            return new PostNotFoundException("Post not found by id");
        });
        User user = userService.getUserByUsername(jwtUtil.getUsernameFromToken());
        Comment comment = commentRepository.save(commentMapstruct.toEntity(request, post, user));
        Optional<List<Comment>> optionalComments = commentRepository.findCommentByPost(comment.getPost());
        List<Comment> comments = new ArrayList<>();
        if (optionalComments.isPresent()) {
            comments = optionalComments.get();
        }
        return commentMapstruct.mapToComments(comments);
    }

    @Override
    public IdResponse deleteComment(IdRequest request) {
        User user = userService.getUserByUsername(jwtUtil.getUsernameFromToken());
        commentRepository.findByIdAndUser(request.getId(), user).orElseThrow(() -> {
            log.error("Comment not found by id: " + request.getId());
            return new CommentNotFoundException("Comment not found by id");
        });
        commentRepository.deleteById(request.getId());
        return IdResponse.builder()
                .id(request.getId())
                .build();
    }
}
