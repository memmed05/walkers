package com.example.walkers.dto.post;

import com.example.walkers.dto.comment.GetCommentsOfPostByIdResponse;
import com.example.walkers.model.Post;

import java.util.List;


public record GetPostById(
        String username,
        String postUrl,
        String description,
        List<GetCommentsOfPostByIdResponse> comments,
        Integer likes
) {
    public static GetPostById getPost(Post post) {
        List<GetCommentsOfPostByIdResponse> commentsOfPostById = post.getComments().stream()
                .map(GetCommentsOfPostByIdResponse::convertToGetResponse)
                .toList();
        return new GetPostById(
                post.getUser().getUsername(),
                post.getPostUrl(),
                post.getDescription(),
                commentsOfPostById,
                post.getLikes().size()
        );
    }
}
