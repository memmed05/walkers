package com.example.walkers.service;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.IdResponse;
import com.example.walkers.dto.post.GetPostById;
import com.example.walkers.dto.post.PostSaveDto;
import com.example.walkers.dto.post.PostUpdateRequest;

import java.util.UUID;

public interface PostService {
    PostSaveDto savePost(PostSaveDto post);

    IdResponse deletePost(UUID id);

    PostSaveDto updatePost(PostUpdateRequest postRequest, UUID id);

    GetPostById getPostById(IdRequest request);
}
