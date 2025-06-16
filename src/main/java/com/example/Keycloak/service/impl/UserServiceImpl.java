package com.example.Keycloak.service.impl;

import com.example.Keycloak.DTO.UserDTO;
import com.example.Keycloak.mapper.UserMapper;
import com.example.Keycloak.model.User;
import com.example.Keycloak.repository.UserRepository;
import com.example.Keycloak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getCurrentUser() {
        User user = new User();

        User saveUser = userRepository.save(user);

        return userMapper.toDTO(saveUser);
    }

    @Override
    public boolean userExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            return false;
        }
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }
}
