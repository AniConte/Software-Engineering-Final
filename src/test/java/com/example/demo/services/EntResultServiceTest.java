package com.example.demo.services;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.DTOs.EntResultDTO;
import com.example.demo.entities.EntSubject;
import com.example.demo.entities.User;
import com.example.demo.repositories.EntSubjectRepo;
import com.example.demo.repositories.UserRepo;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class EntResultServiceTest {

    @Autowired
    private EntResultService service;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EntSubjectRepo subjectRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCreateUpdateDelete() {
        User user = new User();
        user.setName("Beka Hans");
        user.setEmail("beka@gmail.com");
        user.setSex("M");
        user.setUsername("beka");
        user.setPassword(passwordEncoder.encode("password"));
        user = userRepo.save(user);

        EntSubject subject = new EntSubject();
        subject.setName("Math");
        subject = subjectRepo.save(subject);

        EntResultDTO dto = new EntResultDTO();
        dto.setExamDate(LocalDate.now());
        dto.setScore(120);
        dto.setUserId(user.getId());
        dto.setSubjectId(subject.getId());

        EntResultDTO created = service.create(dto);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals(dto.getScore(), created.getScore());
        Assertions.assertEquals(user.getId(), created.getUserId());
        Assertions.assertEquals(subject.getId(), created.getSubjectId());

        EntResultDTO found = service.getById(created.getId());
        Assertions.assertEquals(created.getId(), found.getId());

        EntResultDTO updateDto = new EntResultDTO();
        updateDto.setScore(125);

        EntResultDTO updated = service.update(created.getId(), updateDto);
        Assertions.assertEquals(Integer.valueOf(125), updated.getScore());

        service.delete(created.getId());
        jakarta.persistence.EntityNotFoundException exception = Assertions.assertThrows(
                jakarta.persistence.EntityNotFoundException.class,
                () -> service.getById(created.getId()));
        Assertions.assertNotNull(exception);
    }
}