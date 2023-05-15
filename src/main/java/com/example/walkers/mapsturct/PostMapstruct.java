package com.example.walkers.mapsturct;

import com.example.walkers.dto.comment.GetCommentsOfPostByIdResponse;
import com.example.walkers.dto.post.GetPostById;
import com.example.walkers.dto.post.PostSaveDto;
import com.example.walkers.model.Comment;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PostMapstruct {

    @Mapping(target = "postUrl", source = "post.postUrl")
    @Mapping(target = "description", source = "post.description")
    @Mapping(target = "user", source = "user")
    public abstract Post toEntity(PostSaveDto post, User user);

    @Mapping(target = "postUrl", source = "postUrl")
    @Mapping(target = "description", source = "description")
    public abstract PostSaveDto mapToGet(Post post);

    @Mapping(target = "postUrl", source = "postUrl")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "comments", source = "comments", qualifiedByName = "getComments")
    public abstract GetPostById mapToGetAll(Post post);

    @Named("getComments")
    public List<GetCommentsOfPostByIdResponse> getComments(List<Comment> comments) {
        return comments.stream()
                .map((comment) -> GetCommentsOfPostByIdResponse.builder()
                        .comment(comment.getComment())
                        .dayAgo(ChronoUnit.DAYS.between(LocalDateTime.now(), comment.getCreatedAt()))
                        .build())
                .toList();
    }
}
