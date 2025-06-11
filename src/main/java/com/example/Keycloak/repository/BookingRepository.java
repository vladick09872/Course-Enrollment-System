package com.example.Keycloak.repository;

import com.example.Keycloak.DTO.BookingDTO;
import com.example.Keycloak.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByUserEmail(String userEmail);

    Long id(Long id);
}
