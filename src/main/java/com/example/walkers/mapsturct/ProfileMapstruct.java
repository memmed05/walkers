package com.example.walkers.mapsturct;

import com.example.walkers.dto.profile.GetProfileResponse;
import com.example.walkers.dto.profile.SaveProfileRequest;
import com.example.walkers.model.Profile;
import com.example.walkers.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ProfileMapstruct {

    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "surname", source = "request.surname")
    @Mapping(target = "patronymic", source = "request.patronymic")
    @Mapping(target = "bio", source = "request.bio")
    @Mapping(target = "ppUrl", source = "request.ppUrl")
    @Mapping(target = "user", source = "user")
    public abstract Profile toEntity(SaveProfileRequest request, User user);

    @Mapping(target = "name", source = "profile.name")
    @Mapping(target = "surname", source = "profile.surname")
    @Mapping(target = "patronymic", source = "profile.patronymic")
    @Mapping(target = "bio", source = "profile.bio")
    @Mapping(target = "ppUrl", source = "profile.ppUrl")
    public abstract GetProfileResponse mapToGet(Profile profile);


}
