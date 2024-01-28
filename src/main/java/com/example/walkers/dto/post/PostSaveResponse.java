package com.example.walkers.dto.post;

import com.example.walkers.model.Post;

import java.util.UUID;

public record PostSaveResponse(

        UUID postId,
        String postUrl,
        String description,
        String username
) {

    public static PostSaveResponse map(Post post){
        return new PostSaveResponse(
                post.getId(),
                post.getPostUrl(),
                post.getDescription(),
                post.getUser().getUsername()
        );
    }
}
