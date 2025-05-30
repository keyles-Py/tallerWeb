package com.app.citas.service;

import com.app.citas.dto.UserDTO;
import com.app.citas.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);
    void register(UserDTO userDTO) throws Exception;

    void deleteUser(Long id) throws Exception;
}
