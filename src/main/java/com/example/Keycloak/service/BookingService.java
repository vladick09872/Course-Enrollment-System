package com.example.Keycloak.service;

import com.example.Keycloak.DTO.BookingDTO;
import com.example.Keycloak.DTO.RoomDTO;
import com.example.Keycloak.mapper.BookingMapper;
import com.example.Keycloak.mapper.RoomMapper;
import com.example.Keycloak.model.Booking;
import com.example.Keycloak.repository.BookingRepository;
import com.example.Keycloak.repository.MeetingRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final RoomMapper roomMapper;
    private final MeetingRoomRepository meetingRoomRepository;

    public BookingDTO createBooking(Booking booking) {
        return bookingMapper.toDTO(bookingRepository.save(booking));
    }

    public List<BookingDTO> findAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDTO)
                .toList();
    }

    @Transactional
    public List<RoomDTO> findAvailableRoom(LocalDateTime startTime, LocalDateTime endTime) {
        return meetingRoomRepository.findAll().stream()
                .filter(room -> room.getBookings().stream()
                        .noneMatch(booking -> booking.getStartTime().isBefore(endTime) && booking.getEndTime().isAfter(startTime)))
                .map(roomMapper::toDTO)
                .toList();
    }

    public Optional<BookingDTO> findBookingsByUserEmail(String userEmail) {
        return bookingRepository.findByUserEmail(userEmail)
                .map(bookingMapper::toDTO);
    }

    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    public Optional<BookingDTO> findBookingByRoomId(Long roomId) {
        return bookingRepository.findById(roomId)
                .map(bookingMapper::toDTO);
    }
}
