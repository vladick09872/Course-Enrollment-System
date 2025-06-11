package com.example.Keycloak.controller;

import com.example.Keycloak.DTO.BookingDTO;
import com.example.Keycloak.DTO.RoomDTO;
import com.example.Keycloak.model.Booking;
import com.example.Keycloak.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/bookings")
    @PreAuthorize("hasRole('ADMIN')")
    public BookingDTO createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/bookings/all")
    @PreAuthorize("hasRole('USER')")
    public List<BookingDTO> getAllBookings() {
        return bookingService.findAllBookings();
    }

    @DeleteMapping("/bookings/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }

    @GetMapping("/bookings/room/{roomId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Optional<BookingDTO> getByRoomId(@PathVariable Long roomId) {
        return bookingService.findBookingByRoomId(roomId);
    }

    @GetMapping("/bookings/user/{userEmail}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<BookingDTO> getByUserEmail(@PathVariable String userEmail) {
        return bookingService.findBookingsByUserEmail(userEmail);
    }

    @GetMapping("/bookings/room/available")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<RoomDTO> getAvailableRoom(@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {
        return bookingService.findAvailableRoom(startTime, endTime);
    }
}
