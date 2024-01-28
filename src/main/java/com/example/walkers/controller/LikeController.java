package com.example.walkers.controller;

import com.example.walkers.dto.like.AddLikeRequest;
import com.example.walkers.dto.like.LikeDto;
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

    @PostMapping("/{post}")
    public ResponseEntity<Integer> addLikeToPost(@PathVariable(name = "post") UUID id) {
        return new ResponseEntity<>(likeService.addLike(new AddLikeRequest(id)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{post}")
    public ResponseEntity<Boolean> deleteLikeFromPost(@PathVariable(name = "post") UUID id){
        return new ResponseEntity<>(likeService.deleteLike(id), HttpStatus.OK);
    }
}
