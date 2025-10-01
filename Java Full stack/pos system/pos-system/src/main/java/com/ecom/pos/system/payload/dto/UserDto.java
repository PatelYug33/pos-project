package com.ecom.pos.system.payload.dto;

import com.ecom.pos.system.domain.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;

    private String fullname;

    private String email;

    private String phone;

    private UserRole role;

    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
}
