package com.example.walkers.controller;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.post.PostSaveResponse;
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
    public ResponseEntity<PostSaveResponse> savePost(@Valid @RequestBody PostSaveDto post){
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostSaveResponse> updatePost(@Valid @RequestBody PostUpdateRequest postRequest,
                                                  @PathVariable UUID id){
        return ResponseEntity.ok(postService.updatePost(postRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPostById> getPostById(@PathVariable UUID id){
        return ResponseEntity.ok(postService.getPostById(new IdRequest(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable UUID id){
        return ResponseEntity.ok(postService.deletePost(id));
    }


}
