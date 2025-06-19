package com.example.Keycloak.DTO;

import java.time.LocalDate;

public record ResponseDTO(Long id,
                          String text,
                          LocalDate createdAt,
                          UserShortDTO responder,
                          ComplaintShortDTO complaint) {
}
