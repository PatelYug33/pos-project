package com.ecom.pos.system.service;

import com.ecom.pos.system.exceptions.UserException;
import com.ecom.pos.system.payload.dto.UserDto;
import com.ecom.pos.system.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;

}
