package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTOs.EntSubjectDTO;
import com.example.demo.entities.EntSubject;
import com.example.demo.mappers.EntSubjectMapper;
import com.example.demo.repositories.EntSubjectRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntSubjectService {
    private final EntSubjectRepo repo;
    private final EntSubjectMapper mapper;

    public List<EntSubjectDTO> getAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public EntSubjectDTO getById(Long id) {
        EntSubject e = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toDTO(e);
    }

    public EntSubjectDTO create(EntSubjectDTO dto) {
        EntSubject e = mapper.toEntity(dto);
        e = repo.save(e);
        return mapper.toDTO(e);
    }

    public EntSubjectDTO update(Long id, EntSubjectDTO dto) {
        EntSubject e = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        if (dto.getName() != null) {
            e.setName(dto.getName());
        }
        e = repo.save(e);
        return mapper.toDTO(e);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}