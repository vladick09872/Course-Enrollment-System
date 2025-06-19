package com.example.Keycloak.service;

import com.example.Keycloak.DTO.UserDTO;
import com.example.Keycloak.DTO.UserShortDTO;

import java.util.List;

public interface UserService {
    UserShortDTO getCurrentUser();

    List<UserShortDTO> findAllUsers();
}
