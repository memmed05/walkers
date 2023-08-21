package com.example.walkers.dto.post;

import com.example.walkers.dto.comment.GetCommentsOfPostByIdResponse;
import com.example.walkers.model.Post;

import java.util.List;
import java.util.stream.Collectors;


public record GetPostById(
        String postUrl,
        String description,
        List<GetCommentsOfPostByIdResponse> comments
) {
    public static GetPostById getPost(Post post) {
        List<GetCommentsOfPostByIdResponse> commentsOfPostById = post.getComments().stream()
                .map(GetCommentsOfPostByIdResponse::convertToGetReponse)
                .toList();
        return new GetPostById(
                post.getPostUrl(),
                post.getDescription(),
                commentsOfPostById
        );
    }
}
