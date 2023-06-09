package com.example.walkers.controller;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.IdResponse;
import com.example.walkers.dto.post.GetPostById;
import com.example.walkers.dto.post.PostSaveDto;
import com.example.walkers.dto.post.PostUpdateRequest;
import com.example.walkers.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostSaveDto> savePost(@Valid @RequestBody PostSaveDto post){
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostSaveDto> updatePost(@Valid @RequestBody PostUpdateRequest postRequest,
                                                  @PathVariable UUID id){
        return ResponseEntity.ok(postService.updatePost(postRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPostById> getPostById(@PathVariable UUID id){
        return ResponseEntity.ok(postService.getPostById(IdRequest.builder()
                .id(id)
                .build()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IdResponse> deletePost(@PathVariable UUID id){
        return ResponseEntity.ok(postService.deletePost(id));
    }


}
