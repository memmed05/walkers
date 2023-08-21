package com.example.walkers.dto.like;

import com.example.walkers.model.Like;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;

import java.util.UUID;

public record AddLikeRequest(
        UUID id
) {
    public static Like converToEnt(User user, Post post) {
        return new Like(
                null,
                null,
                null,
                post,
                user
        );
    }
}
