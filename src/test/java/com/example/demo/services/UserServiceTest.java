package com.example.demo.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.DTOs.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepo;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCreateReadUpdateDelete() {
        UserDTO dto = new UserDTO();
        dto.setFullname("Alice");
        dto.setEmail("alice@gmail.com");
        dto.setUsername("alice");
        dto.setPassword(passwordEncoder.encode("secret"));

        UserDTO created = userService.create(dto);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals("Alice", created.getFullname());
        Assertions.assertEquals("alice@gmail.com", created.getEmail());
        Assertions.assertEquals("alice", created.getUsername());

        UserDTO found = userService.getUserDTO(created.getId());
        Assertions.assertEquals(created.getId(), found.getId());
        Assertions.assertEquals(created.getFullname(), found.getFullname());

        UserDTO updateDto = new UserDTO();
        updateDto.setFullname("Alice Updated");
        updateDto.setEmail("alice2@gmail.com");

        UserDTO updated = userService.update(created.getId(), updateDto);
        Assertions.assertEquals("Alice Updated", updated.getFullname());
        Assertions.assertEquals("alice2@gmail.com", updated.getEmail());
        Assertions.assertEquals("alice", updated.getUsername());

        userService.delete(created.getId());
        Optional<User> afterDelete = userRepo.findById(created.getId());
        Assertions.assertTrue(afterDelete.isEmpty());
    }
}