package com.example.demo.DTOs;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EntResultDTO {
    private Long id;
    private LocalDate examDate;
    private Integer score;
    private Long userId;
    private Long subjectId;
}
