package com.example.Keycloak.mapper;

import com.example.Keycloak.DTO.BookingDTO;
import com.example.Keycloak.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toEntity(BookingDTO bookingDTO);

    BookingDTO toDTO(Booking booking);
}
