package com.ecom.pos.system.controller;

import com.ecom.pos.system.exceptions.UserException;
import com.ecom.pos.system.mapper.UserMapper;
import com.ecom.pos.system.model.User;
import com.ecom.pos.system.payload.dto.UserDto;
import com.ecom.pos.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader ("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return  ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@RequestHeader ("Authorization") String jwt, @PathVariable Long id) throws UserException, Exception {
        User user = userService.getUserById(id);
        return  ResponseEntity.ok(UserMapper.toDTO(user));
    }
}
