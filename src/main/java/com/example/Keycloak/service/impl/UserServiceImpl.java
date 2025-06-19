package com.example.Keycloak.service.impl;

import com.example.Keycloak.DTO.UserDTO;
import com.example.Keycloak.DTO.UserShortDTO;
import com.example.Keycloak.mapper.UserMapper;
import com.example.Keycloak.model.User;
import com.example.Keycloak.repository.UserRepository;
import com.example.Keycloak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserShortDTO getCurrentUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        return userMapper.toDTO(user);
    }

    @Override
    public List<UserShortDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }
}
