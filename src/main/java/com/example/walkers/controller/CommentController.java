package com.example.walkers.controller;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.IdResponse;
import com.example.walkers.dto.comment.GetCommentsOfPostByIdRequest;
import com.example.walkers.dto.comment.GetCommentsOfPostByIdResponse;
import com.example.walkers.dto.comment.SaveCommentRequest;
import com.example.walkers.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{post}")
    public ResponseEntity<List<GetCommentsOfPostByIdResponse>> getCommentsOfPostById(@PathVariable(name = "post") UUID postId, @RequestParam UUID id) {
        return ResponseEntity.ok(commentService.getCommentsOfPostById(new GetCommentsOfPostByIdRequest(id, postId)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<List<GetCommentsOfPostByIdResponse>> addCommentToPost(@PathVariable UUID id, @RequestBody SaveCommentRequest request) {
        return new ResponseEntity<>(commentService.addComment(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IdResponse> deleteComment(@PathVariable UUID id) {
        return ResponseEntity.ok(commentService.deleteComment(new IdRequest(id)));
    }
}
