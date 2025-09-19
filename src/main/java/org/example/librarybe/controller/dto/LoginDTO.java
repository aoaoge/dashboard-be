package org.example.librarybe.controller.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private String token;
    private boolean status;
}
