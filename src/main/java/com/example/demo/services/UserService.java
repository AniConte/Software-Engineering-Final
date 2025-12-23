package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTOs.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.UserRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUserDTOs() {
        return userRepo.findAll().stream().map(userMapper::toDTO).toList();
    }

    public UserDTO getUserDTO(Long id) {
        User user = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return userMapper.toDTO(user);
    }

    public UserDTO create(UserDTO dto) {
        User e = userMapper.toUserEntity(dto);
        e = userRepo.save(e);
        return userMapper.toDTO(e);
    }

    public UserDTO update(Long id, UserDTO dto) {
        User e = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        if (dto.getFullname() != null) {
            e.setName(dto.getFullname());
        }
        if (dto.getEmail() != null) {
            e.setEmail(dto.getEmail());
        }
        e = userRepo.save(e);
        return userMapper.toDTO(e);
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}