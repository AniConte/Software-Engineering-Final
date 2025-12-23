package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.EntSubjectDTO;
import com.example.demo.services.EntSubjectService;

@RestController
@RequestMapping("/api/subjects")
public class EntSubjectController {
    private final EntSubjectService service;

    public EntSubjectController(EntSubjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<EntSubjectDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EntSubjectDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public EntSubjectDTO create(@RequestBody EntSubjectDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public EntSubjectDTO update(@PathVariable Long id, @RequestBody EntSubjectDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
