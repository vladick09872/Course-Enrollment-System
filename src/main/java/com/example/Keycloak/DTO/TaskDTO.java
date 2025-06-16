package com.example.Keycloak.DTO;

import java.time.LocalDate;

public record TaskDTO(Long id,
                      String title,
                      String description,
                      LocalDate createdAt,
                      LocalDate updatedAt,
                      UserDTO owner) {
}
