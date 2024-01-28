package com.example.walkers.service;

import com.example.walkers.dto.like.AddLikeRequest;

import java.util.UUID;

public interface LikeService {
    Integer addLike(AddLikeRequest request);

    Boolean deleteLike(UUID id);
}
