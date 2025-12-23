package com.example.demo.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.DTOs.EntSubjectDTO;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class EntSubjectServiceTest {

    @Autowired
    private EntSubjectService service;

    @Test
    public void testCreateReadUpdateDelete() {
        EntSubjectDTO dto = new EntSubjectDTO();
        dto.setName("Calculus 1");

        EntSubjectDTO created = service.create(dto);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals("Calculus 1", created.getName());

        EntSubjectDTO found = service.getById(created.getId());
        Assertions.assertEquals(created.getId(), found.getId());
        Assertions.assertEquals("Calculus 1", found.getName());

        List<EntSubjectDTO> all = service.getAll();
        boolean contains = all.stream().anyMatch(s -> s.getId().equals(created.getId()));
        Assertions.assertTrue(contains);

        EntSubjectDTO updateDto = new EntSubjectDTO();
        updateDto.setName("Calculus 2");

        EntSubjectDTO updated = service.update(created.getId(), updateDto);
        Assertions.assertEquals("Calculus 2", updated.getName());

        service.delete(created.getId());
        Assertions.assertThrows(
                jakarta.persistence.EntityNotFoundException.class,
                () -> service.getById(created.getId()));
    }
}