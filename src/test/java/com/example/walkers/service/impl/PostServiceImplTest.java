package com.example.walkers.service.impl;

import com.example.walkers.dto.post.PostSaveDto;
import com.example.walkers.dto.post.PostSaveResponse;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import com.example.walkers.repository.PostRepository;
import com.example.walkers.security.JwtFilter;
import com.example.walkers.service.UserService;
import com.example.walkers.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void savePost_ShouldReturnPostSaveResponse() {
        // Arrange
        String username = "testuser";
        PostSaveDto postDto = new PostSaveDto("url", "desc");
        User mockUser = new User();
        Post postEntity = new Post();
        Post savedPost = new Post();
        PostSaveResponse expectedResponse = new PostSaveResponse(
                UUID.fromString("51cb1414-67a3-4f31-ae34-f0d7ef047d69"),
                        "url", "desc", "username");

        Mockito.when(jwtUtil.getUsernameFromToken()).thenReturn(username);
        Mockito.when(userService.getUserByUsernameOrEmail(username)).thenReturn(mockUser);
        Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(savedPost);
        Mockito.mockStatic(PostSaveDto.class).when(() -> PostSaveDto.toEntity(postDto, mockUser)).thenReturn(postEntity);
        Mockito.mockStatic(PostSaveResponse.class).when(() -> PostSaveResponse.map(savedPost)).thenReturn(expectedResponse);

        // Act
        PostSaveResponse result = postService.savePost(postDto);

        // Assert
        Assertions.assertEquals(expectedResponse, result);
        Mockito.verify(postRepository, Mockito.times(1)).save(Mockito.any(Post.class));
    }
}