package com.example.walkers.mapsturct;

import com.example.walkers.dto.user.UserRegisterRequest;
import com.example.walkers.model.Role;
import com.example.walkers.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", imports = {Role.class})
public abstract class UserMapstruct {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "email",source = "request.email")
    @Mapping(target = "username",source = "request.username")
    @Mapping(target = "password",source = "request.password", qualifiedByName = "encryptPassword")
    @Mapping(target = "role", expression = "java(Role.USER)")
    @Mapping(target = "confirmed", constant = "false")
    public abstract User toEntity(UserRegisterRequest request);


    @Named("encryptPassword")
    protected String  encryptPassword(String password){
        return passwordEncoder.encode(password);
    }
}
