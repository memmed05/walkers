package com.example.walkers.service;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.IdResponse;
import com.example.walkers.dto.comment.GetCommentsOfPostByIdRequest;
import com.example.walkers.dto.comment.GetCommentsOfPostByIdResponse;
import com.example.walkers.dto.comment.SaveCommentRequest;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    List<GetCommentsOfPostByIdResponse> getCommentsOfPostById(GetCommentsOfPostByIdRequest request);

    List<GetCommentsOfPostByIdResponse> addComment(UUID id, SaveCommentRequest request);

    IdResponse deleteComment(IdRequest request);
}
