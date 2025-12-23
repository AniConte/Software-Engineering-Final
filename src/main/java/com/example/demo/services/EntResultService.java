package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTOs.EntResultDTO;
import com.example.demo.entities.EntResult;
import com.example.demo.entities.EntSubject;
import com.example.demo.entities.User;
import com.example.demo.mappers.EntResultMapper;
import com.example.demo.repositories.EntResultRepo;
import com.example.demo.repositories.EntSubjectRepo;
import com.example.demo.repositories.UserRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntResultService {
    private final EntResultRepo repo;
    private final UserRepo userRepo;
    private final EntSubjectRepo subjectRepo;
    private final EntResultMapper mapper;

    public List<EntResultDTO> getAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public EntResultDTO getById(Long id) {
        EntResult e = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toDTO(e);
    }

    public EntResultDTO create(EntResultDTO dto) {
        User u = userRepo.findById(dto.getUserId()).orElseThrow(EntityNotFoundException::new);
        EntSubject s = subjectRepo.findById(dto.getSubjectId()).orElseThrow(EntityNotFoundException::new);
        EntResult e = mapper.toEntity(dto);
        e.setUser(u);
        e.setSubject(s);
        e = repo.save(e);
        return mapper.toDTO(e);
    }

    public EntResultDTO update(Long id, EntResultDTO dto) {
        EntResult e = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        if (dto.getUserId() != null) {
            User u = userRepo.findById(dto.getUserId()).orElseThrow(EntityNotFoundException::new);
            e.setUser(u);
        }
        if (dto.getSubjectId() != null) {
            EntSubject s = subjectRepo.findById(dto.getSubjectId()).orElseThrow(EntityNotFoundException::new);
            e.setSubject(s);
        }
        if (dto.getExamDate() != null) {
            e.setDate(dto.getExamDate());
        }
        if (dto.getScore() != null) {
            e.setScore(dto.getScore());
        }
        e = repo.save(e);
        return mapper.toDTO(e);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}