package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.EntResult;

@Repository
public interface EntResultRepo extends JpaRepository<EntResult, Long> {

}
