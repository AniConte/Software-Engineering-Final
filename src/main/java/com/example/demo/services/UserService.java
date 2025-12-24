package com.example.demo.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTOs.UserDTO;
import com.example.demo.entities.AppPermission;
import com.example.demo.entities.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.AppPermissionRepo;
import com.example.demo.repositories.UserRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final AppPermissionRepo permissionRepo;

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

    public List<String> getPermissions(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        return user.getPermissions().stream().map(AppPermission::getName).sorted().toList();
    }

    public List<String> getPermissionsByUsername(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        return user.getPermissions().stream().map(AppPermission::getName).sorted().toList();
    }

    public List<String> getAllPermissionNames() {
        return permissionRepo.findAll().stream().map(AppPermission::getName).sorted().toList();
    }

    @Transactional
    public List<String> grantPermissions(Long userId, List<String> permissionNames) {
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        Set<AppPermission> toGrant = resolvePermissions(permissionNames);
        user.getPermissions().addAll(toGrant);
        userRepo.save(user);
        return user.getPermissions().stream().map(AppPermission::getName).sorted().toList();
    }

    @Transactional
    public List<String> setPermissions(Long userId, List<String> permissionNames) {
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        Set<AppPermission> resolved = resolvePermissions(permissionNames);
        user.getPermissions().clear();
        user.getPermissions().addAll(resolved);
        userRepo.save(user);
        return user.getPermissions().stream().map(AppPermission::getName).sorted().toList();
    }

    @Transactional
    public List<String> revokePermissions(Long userId, List<String> permissionNames) {
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        Set<AppPermission> toRevoke = resolvePermissions(permissionNames);
        user.getPermissions().removeAll(toRevoke);
        userRepo.save(user);
        return user.getPermissions().stream().map(AppPermission::getName).sorted().toList();
    }

    private Set<AppPermission> resolvePermissions(List<String> names) {
        if (names == null || names.isEmpty()) {
            return Set.of();
        }
        return names.stream()
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .distinct()
                .map(name -> permissionRepo.findByName(name).orElseThrow(EntityNotFoundException::new))
                .collect(java.util.stream.Collectors.toSet());
    }
}