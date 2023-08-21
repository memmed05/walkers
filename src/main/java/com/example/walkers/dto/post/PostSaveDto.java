package com.example.walkers.dto.post;

import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record PostSaveDto(
        @NotBlank
        String postUrl,
        @Nullable
        String description
) {
    public static Post toEntity(PostSaveDto post, User user) {
        return new Post(
                null,
                null,
                null,
                post.postUrl,
                post.description,
                null,
                null,
                user
        );
    }

    public static PostSaveDto toGet(Post post) {
        return new PostSaveDto(
                post.getPostUrl(),
                post.getDescription()
        );
    }
}
