package com.example.Keycloak.DTO;

import java.time.LocalDate;
import java.util.List;

public record ComplaintDTO(Long id,
                           String title,
                           String description,
                           LocalDate createdAt,
                           LocalDate updatedAt,
                           UserShortDTO userShortDTO,
                           UserShortDTO assignedTo,
                           List<ResponseShortDTO> responses) {
}
