package com.example.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @PutMapping("/greet")
    public String sayGreet() {
        return "Greeting from 2025!";
    }
}