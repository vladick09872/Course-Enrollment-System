package com.example.Keycloak.service;

import com.example.Keycloak.DTO.RoomDTO;
import com.example.Keycloak.mapper.RoomMapper;
import com.example.Keycloak.model.MeetingRoom;
import com.example.Keycloak.repository.MeetingRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingRoomService {
    private final MeetingRoomRepository meetingRoomRepository;
    private final RoomMapper roomMapper;

    public List<RoomDTO> findAllRooms() {
        return meetingRoomRepository.findAll().stream().map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RoomDTO findRoomById(Long roomId) {
        return meetingRoomRepository.findById(roomId)
                .map(roomMapper::toDTO)
                .orElse(null);
    }

    public RoomDTO createRoom(MeetingRoom meetingRoom) {
        return roomMapper.toDTO(meetingRoomRepository.save(meetingRoom));
    }

    @Transactional
    public RoomDTO updateRoom(Long roomId, MeetingRoom room) {
        MeetingRoom meetingRoom = meetingRoomRepository.findById(roomId)
                .orElse(null);

        if (meetingRoom != null) {
            meetingRoom.setName(room.getName());
            meetingRoom.setCapacity(room.getCapacity());
            meetingRoom.setLocation(room.getLocation());
            return roomMapper.toDTO(meetingRoomRepository.save(room));
        } else {
            return null;
        }
    }

    public void deleteRoom(Long id) {
        RoomDTO dto = roomMapper.toDTO(meetingRoomRepository.findById(id)
                .orElse(null));
    }
}
