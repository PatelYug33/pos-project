package com.ecom.pos.system.service;

import com.ecom.pos.system.exceptions.UserException;
import com.ecom.pos.system.model.User;

import java.util.List;

public interface UserService {
    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id) throws UserException, Exception;
    List<User> getAllUser();

}
