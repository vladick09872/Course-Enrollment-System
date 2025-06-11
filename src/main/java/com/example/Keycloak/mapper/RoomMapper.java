package com.example.Keycloak.mapper;

import com.example.Keycloak.DTO.RoomDTO;
import com.example.Keycloak.model.MeetingRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDTO toDTO(MeetingRoom meetingRoom);

    MeetingRoom toEntity(RoomDTO roomDTO);
}
