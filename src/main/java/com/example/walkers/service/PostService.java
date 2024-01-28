package com.example.walkers.service;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.post.PostSaveResponse;
import com.example.walkers.dto.post.GetPostById;
import com.example.walkers.dto.post.PostSaveDto;
import com.example.walkers.dto.post.PostUpdateRequest;

import java.util.UUID;

public interface PostService {
    PostSaveResponse savePost(PostSaveDto post);

    Boolean deletePost(UUID id);

    PostSaveResponse updatePost(PostUpdateRequest postRequest, UUID id);

    GetPostById getPostById(IdRequest request);
}
