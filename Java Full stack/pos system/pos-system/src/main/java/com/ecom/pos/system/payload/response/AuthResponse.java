package com.ecom.pos.system.payload.response;

import com.ecom.pos.system.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private UserDto user;
}
