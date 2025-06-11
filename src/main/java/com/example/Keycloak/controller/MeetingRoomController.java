package com.example.Keycloak.controller;

import com.example.Keycloak.DTO.RoomDTO;
import com.example.Keycloak.model.MeetingRoom;
import com.example.Keycloak.service.MeetingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MeetingRoomController {
    private final MeetingRoomService service;

    @PostMapping("/rooms")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomDTO createRoom(@RequestBody MeetingRoom room) {
        return service.createRoom(room);
    }

    @GetMapping("/rooms")
    @PreAuthorize("hasRole('USER')")
    public List<RoomDTO> getAllRooms() {
        return service.findAllRooms();
    }

    @GetMapping("/rooms/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public RoomDTO getRoomById(@PathVariable Long id) {
        return service.findRoomById(id);
    }

    @PutMapping("/rooms/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomDTO updateRoom(@PathVariable Long id, @RequestBody MeetingRoom room) {
        return service.updateRoom(id, room);
    }

    @DeleteMapping("/rooms/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.deleteRoom(id);
    }
}
