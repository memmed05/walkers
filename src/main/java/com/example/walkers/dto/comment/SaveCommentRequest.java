package com.example.walkers.dto.comment;

import com.example.walkers.model.Comment;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;

public record SaveCommentRequest(
        String comment
) {
    public static Comment convertToEnt(SaveCommentRequest request, Post post, User user) {
        return new Comment(
                null,
                null,
                null,
                request.comment,
                post,
                user
        );
    }
}
