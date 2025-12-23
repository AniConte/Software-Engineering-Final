package com.example.demo.DTOs;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String fullname;
    private String email;
    private String username;
    private String password;
}
