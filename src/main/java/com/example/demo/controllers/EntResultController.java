package com.example.demo.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.EntResultDTO;
import com.example.demo.services.EntResultService;

@RestController
@RequestMapping("/api/ent-results")
@PreAuthorize("isAuthenticated()")
public class EntResultController {
    private final EntResultService service;

    public EntResultController(EntResultService service) {
        this.service = service;
    }

    @GetMapping
    public List<EntResultDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EntResultDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public EntResultDTO create(@RequestBody EntResultDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public EntResultDTO update(@PathVariable Long id, @RequestBody EntResultDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
