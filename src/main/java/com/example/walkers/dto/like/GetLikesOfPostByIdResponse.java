package com.example.walkers.dto.like;

import com.example.walkers.model.Like;

import java.util.List;

public record GetLikesOfPostByIdResponse(
        Integer likes
) {

    public static GetLikesOfPostByIdResponse convert(List<Like> likes) {
        return new GetLikesOfPostByIdResponse(likes.size());
    }
}
