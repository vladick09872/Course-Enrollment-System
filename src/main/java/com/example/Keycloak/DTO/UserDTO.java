package com.example.Keycloak.DTO;


public record UserDTO(Long id,
                      String username,
                      String email,
                      String password) {
}
