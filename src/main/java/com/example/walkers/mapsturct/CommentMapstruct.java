package com.example.walkers.mapsturct;

import com.example.walkers.dto.comment.GetCommentsOfPostByIdResponse;
import com.example.walkers.dto.comment.SaveCommentRequest;
import com.example.walkers.model.Comment;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CommentMapstruct {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "comment", source = "request.comment")
    public abstract Comment toEntity(SaveCommentRequest request, Post post, User user);

    public List<GetCommentsOfPostByIdResponse> mapToComments(List<Comment> comments) {
        return comments.stream()
                .map((comment) -> GetCommentsOfPostByIdResponse.builder()
                        .comment(comment.getComment())
                        .dayAgo(ChronoUnit.DAYS.between(LocalDateTime.now(), comment.getCreatedAt()))
                        .build())
                .toList();
    }
}
