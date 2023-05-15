package com.example.walkers.controller;

import com.example.walkers.dto.like.AddLikeRequest;
import com.example.walkers.dto.like.GetLikesOfPostByIdResponse;
import com.example.walkers.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestControllerAdvice
@RequestMapping("/api/v1/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("{post}")
    public ResponseEntity<GetLikesOfPostByIdResponse> addLikeToPost(@PathVariable(name = "post") UUID id) {
        return new ResponseEntity<>(likeService.addLike(AddLikeRequest.builder()
                .id(id)
                .build())
                ,HttpStatus.CREATED);
    }
}
