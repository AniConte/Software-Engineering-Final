package com.example.demo.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.DTOs.UserDTO;
import com.example.demo.entities.User;

@SpringBootTest
@ActiveProfiles("test")
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void testToUserDTO() {
        User user = new User();
        user.setId(1L);
        user.setName("Beka Bekarys");
        user.setEmail("beka@gmail.com");
        user.setUsername("beka");
        user.setPassword("pwd");

        UserDTO dto = mapper.toDTO(user);

        Assertions.assertEquals(Long.valueOf(1L), dto.getId());
        Assertions.assertEquals("Beka Bekarys", dto.getFullname());
        Assertions.assertEquals("beka@gmail.com", dto.getEmail());
        Assertions.assertEquals("beka", dto.getUsername());
        Assertions.assertEquals("pwd", dto.getPassword());
    }

    @Test
    public void testToUserEntity() {
        UserDTO dto = new UserDTO();
        dto.setId(2L);
        dto.setFullname("Janel Guzel");
        dto.setEmail("janel@gmail.com");
        dto.setUsername("janel");
        dto.setPassword("secret");

        User user = mapper.toUserEntity(dto);

        Assertions.assertEquals(Long.valueOf(2L), user.getId());
        Assertions.assertEquals("Janel Guzel", user.getName());
        Assertions.assertEquals("janel@gmail.com", user.getEmail());
        Assertions.assertEquals("janel", user.getUsername());
        Assertions.assertEquals("secret", user.getPassword());
    }
}