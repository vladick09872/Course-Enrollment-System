package com.example.Keycloak.DTO;

import java.time.LocalDateTime;

public record BookingDTO(String userEmail, LocalDateTime startTime, LocalDateTime endTime) {
}
