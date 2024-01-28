package com.example.walkers.service;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.IdResponse;
import com.example.walkers.dto.comment.GetCommentsOfPostByIdResponse;
import com.example.walkers.dto.comment.SaveCommentRequest;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    List<GetCommentsOfPostByIdResponse> getCommentsOfPostById(IdRequest request);

    UUID addComment(UUID id, SaveCommentRequest request);

    Boolean deleteComment(IdRequest request);
}
