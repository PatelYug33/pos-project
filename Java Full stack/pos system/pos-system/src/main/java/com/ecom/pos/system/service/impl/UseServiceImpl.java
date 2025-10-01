package com.ecom.pos.system.service.impl;

import com.ecom.pos.system.config.JwtProvider;
import com.ecom.pos.system.exceptions.UserException;
import com.ecom.pos.system.model.User;
import com.ecom.pos.system.repository.UserRepository;
import com.ecom.pos.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UseServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String token) throws UserException {
        String email = jwtProvider.getEmailFromToken(token);
        User user =userRepository.findByemail(email);
        if(user == null){
            throw  new UserException("Invalid Token");
        }

        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByemail(email);
        if (user == null){
            throw new UserException("user not found");

        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {

        User user = userRepository.findByemail(email);
        if (user == null){
            throw new UserException("user not found");

        }

        return user;
    }

    @Override
    public User getUserById(Long id) throws UserException, Exception {
        return userRepository.findById(id).orElseThrow( ()->new Exception("user not found"));
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
