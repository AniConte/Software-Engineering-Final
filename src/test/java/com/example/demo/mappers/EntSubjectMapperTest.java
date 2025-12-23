package com.example.demo.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.DTOs.EntSubjectDTO;
import com.example.demo.entities.EntSubject;

@SpringBootTest
@ActiveProfiles("test")
public class EntSubjectMapperTest {

    @Autowired
    private EntSubjectMapper mapper;

    @Test
    public void testToDTO() {
        EntSubject subject = new EntSubject();
        subject.setId(1L);
        subject.setName("Philosophy");

        EntSubjectDTO dto = mapper.toDTO(subject);

        Assertions.assertEquals(Long.valueOf(1L), dto.getId());
        Assertions.assertEquals("Philosophy", dto.getName());
    }

    @Test
    public void testToEntity() {
        EntSubjectDTO dto = new EntSubjectDTO();
        dto.setId(2L);
        dto.setName("Philosophy");

        EntSubject entity = mapper.toEntity(dto);

        Assertions.assertEquals(Long.valueOf(2L), entity.getId());
        Assertions.assertEquals("Philosophy", entity.getName());
    }
}