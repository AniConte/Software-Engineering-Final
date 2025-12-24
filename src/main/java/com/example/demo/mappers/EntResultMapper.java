package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.example.demo.DTOs.EntResultDTO;
import com.example.demo.entities.EntResult;

@Mapper(componentModel = "spring")
@Component
public interface EntResultMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "date", target = "examDate")
    EntResultDTO toDTO(EntResult e);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(source = "examDate", target = "date")
    EntResult toEntity(EntResultDTO dto);
}