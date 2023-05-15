package com.example.walkers.service;

import com.example.walkers.dto.like.AddLikeRequest;
import com.example.walkers.dto.like.GetLikesOfPostByIdResponse;

public interface LikeService {
    GetLikesOfPostByIdResponse addLike(AddLikeRequest request);
}
