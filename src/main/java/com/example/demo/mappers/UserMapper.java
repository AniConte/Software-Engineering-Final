package com.example.demo.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.DTOs.UserDTO;
import com.example.demo.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "fullname")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    UserDTO toDTO(User u);

    @InheritInverseConfiguration
    @Mapping(target = "sex", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toUserEntity(UserDTO dto);
}
