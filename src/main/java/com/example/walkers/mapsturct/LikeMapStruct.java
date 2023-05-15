package com.example.walkers.mapsturct;

import com.example.walkers.dto.like.GetLikesOfPostByIdResponse;
import com.example.walkers.model.Like;
import com.example.walkers.model.Post;
import com.example.walkers.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class LikeMapStruct {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "post", source = "post")
    public abstract Like toEntity(Post post, User user);

    public GetLikesOfPostByIdResponse mapToLikes(List<Like> likes) {
        return GetLikesOfPostByIdResponse.builder()
                .likes(likes.size())
                .build();
    }
}
