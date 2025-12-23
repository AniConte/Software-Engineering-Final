package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.example.demo.DTOs.EntSubjectDTO;
import com.example.demo.entities.EntSubject;

@Mapper(componentModel = "spring")
@Component
public interface EntSubjectMapper {
    EntSubjectDTO toDTO(EntSubject e);

    @Mapping(target = "users", ignore = true)
    EntSubject toEntity(EntSubjectDTO dto);
}
