package com.example.walkers.service.impl;

import com.example.walkers.dto.IdRequest;
import com.example.walkers.dto.comment.GetCommentsOfPostByIdResponse;
import com.example.walkers.dto.comment.SaveCommentRequest;
import com.example.walkers.model.Comment;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import com.example.walkers.repository.CommentRepository;
import com.example.walkers.repository.PostRepository;
import com.example.walkers.service.UserService;
import com.example.walkers.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    CommentRepository commentRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    UserService userService;

    @Mock
    JwtUtil jwtUtil;

    @InjectMocks
    CommentServiceImpl commentService;

    @Test
    void addComment_ShouldReturnListOfComments() {
        Post post = Post.builder()
                .id(UUID.fromString("51cb1414-67a3-4f31-ae34-f0d7ef047d69"))
                .build();
        UUID id=UUID.fromString("51cb1414-67a3-4f31-ae34-f0d7ef047d69");
        String username="testUsername";
        User user = User.builder()
                .username(username)
                        .build();

        SaveCommentRequest request=new SaveCommentRequest("test");
        Comment comment= Comment.builder()
                .id(id)
                .comment("test")
                        .build();


        Mockito.when(postRepository.findById(id)).thenReturn(Optional.of(post));
        Mockito.when(jwtUtil.getUsernameFromToken()).thenReturn(username);
        Mockito.when(userService.getUserByUsernameOrEmail(username)).thenReturn(user);
        Mockito.mockStatic(SaveCommentRequest.class).when(()->SaveCommentRequest.convertToEnt(request,post,user))
                .thenReturn(comment);
        Mockito.when(commentRepository.save(comment)).thenReturn(comment);


        commentService.addComment(id,request);

        Assertions.assertEquals(comment.getId(),id);



    }

}