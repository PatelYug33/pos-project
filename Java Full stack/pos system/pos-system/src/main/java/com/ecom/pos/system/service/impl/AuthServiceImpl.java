package com.ecom.pos.system.service.impl;

import com.ecom.pos.system.config.JwtProvider;
import com.ecom.pos.system.domain.UserRole;
import com.ecom.pos.system.exceptions.UserException;
import com.ecom.pos.system.mapper.UserMapper;
import com.ecom.pos.system.model.User;
import com.ecom.pos.system.payload.dto.UserDto;
import com.ecom.pos.system.payload.response.AuthResponse;
import com.ecom.pos.system.repository.UserRepository;
import com.ecom.pos.system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomerUserImpl customerUserImpl;

    @Override
    public AuthResponse signup(UserDto userDto) throws UserException {
        User user =userRepository.findByemail(userDto.getEmail());
        if(user != null){
            throw  new UserException("email id already registered ! ");
        }
        if(userDto.getRole().equals(UserRole.ROLE_ADMIN)){
            throw  new UserException("role admin is not allowed ! ");
        }

        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());
        newUser.setFullname(userDto.getFullname());
        newUser.setPhone(userDto.getPhone());
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(newUser);

        Authentication authentication= new UsernamePasswordAuthenticationToken(userDto.getEmail(),userDto.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse= new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully");
        authResponse.setUser(UserMapper.toDTO(savedUser));



        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) throws UserException {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        Authentication authentication = authenticate(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);

        User user =userRepository.findByemail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse authResponse= new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successfully");
        authResponse.setUser(UserMapper.toDTO(user));


        return authResponse;
    }

    private Authentication authenticate(String email,String password)throws UserException{
        UserDetails userDetails = customerUserImpl.loadUserByUsername(email);
        if(userDetails == null){
            throw  new UserException("email id doesn't exits"+email);
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new UserException("password doesn't match");
        }
        return  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }



}
