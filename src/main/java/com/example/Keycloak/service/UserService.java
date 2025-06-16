package com.example.Keycloak.service;

import com.example.Keycloak.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Long userId);

    UserDTO getCurrentUser();

    boolean userExists(Long userId);

    List<UserDTO> findAllUsers();
}
