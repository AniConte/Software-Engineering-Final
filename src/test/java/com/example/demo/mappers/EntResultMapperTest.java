package com.example.demo.mappers;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.DTOs.EntResultDTO;
import com.example.demo.entities.EntResult;
import com.example.demo.entities.EntSubject;
import com.example.demo.entities.User;

@SpringBootTest
@ActiveProfiles("test")
public class EntResultMapperTest {

    @Autowired
    private EntResultMapper mapper;

    @Test
    public void testToDTO() {
        User user = new User();
        user.setId(10L);

        EntSubject subject = new EntSubject();
        subject.setId(5L);

        EntResult entity = new EntResult();
        entity.setId(1L);
        entity.setDate(LocalDate.of(2025, 1, 1));
        entity.setScore(120);
        entity.setUser(user);
        entity.setSubject(subject);

        EntResultDTO dto = mapper.toDTO(entity);

        Assertions.assertEquals(Long.valueOf(1L), dto.getId());
        Assertions.assertEquals(LocalDate.of(2025, 1, 1), dto.getExamDate());
        Assertions.assertEquals(Integer.valueOf(120), dto.getScore());
        Assertions.assertEquals(Long.valueOf(10L), dto.getUserId());
        Assertions.assertEquals(Long.valueOf(5L), dto.getSubjectId());
    }

    @Test
    public void testToEntity() {
        EntResultDTO dto = new EntResultDTO();
        dto.setId(2L);
        dto.setExamDate(LocalDate.of(2025, 2, 2));
        dto.setScore(130);
        dto.setUserId(7L);
        dto.setSubjectId(8L);

        EntResult entity = mapper.toEntity(dto);

        Assertions.assertEquals(Long.valueOf(2L), entity.getId());
        Assertions.assertEquals(LocalDate.of(2025, 2, 2), entity.getDate());
        Assertions.assertEquals(Integer.valueOf(130), entity.getScore());
        Assertions.assertNull(entity.getUser());
        Assertions.assertNull(entity.getSubject());
    }
}