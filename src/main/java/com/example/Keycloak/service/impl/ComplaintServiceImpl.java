package com.example.Keycloak.service.impl;

import com.example.Keycloak.DTO.ComplaintDTO;
import com.example.Keycloak.DTO.ComplaintShortDTO;
import com.example.Keycloak.mapper.ComplaintMapper;
import com.example.Keycloak.model.Complaint;
import com.example.Keycloak.model.Role;
import com.example.Keycloak.model.Status;
import com.example.Keycloak.model.User;
import com.example.Keycloak.repository.ComplaintRepository;
import com.example.Keycloak.repository.UserRepository;
import com.example.Keycloak.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintMapper complaintMapper;
    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;


    @Override
    public ComplaintShortDTO createComplaint(ComplaintShortDTO complaintDTO, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow();

        Complaint complaint = new Complaint();

        complaint.setCitizen(user);
        complaint.setStatus(Status.APPROVED);

        return complaintMapper.toDTO(complaintRepository.save(complaint));
    }

    @Override
    public List<ComplaintShortDTO> getComplaintForUser(String username) {
        Optional<Complaint> complaints = complaintRepository.findByCitizenUsername(username);

        return complaints
                .stream()
                .map(complaintMapper::toDTO)
                .toList();

    }

    @Override
    public List<ComplaintShortDTO> findAllComplaints() {
        return complaintRepository.findAll()
                .stream()
                .map(complaintMapper::toDTO)
                .toList();
    }

    @Override
    public ComplaintShortDTO assignComplaint(Long complaintId, String officialUsername) {
        User user = userRepository.findByUsername(officialUsername)
                .orElseThrow();

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow();

        complaint.setAssignedTo(user);

        return complaintMapper.toDTO(complaintRepository.save(complaint));
    }

    @Override
    public ComplaintShortDTO updateComplaint(Long complaintId, Status newStatus, String officialUsername) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow();

        if (!complaint.getAssignedTo().getUsername().equals(officialUsername)) {
            throw new AccessDeniedException("Not authorized to update this complaint");
        }

        complaint.setStatus(newStatus);

        return complaintMapper.toDTO(complaintRepository.save(complaint));

    }

    @Override
    public void deleteComplaint(Long complaintId) {
        if (!complaintRepository.existsById(complaintId)) {
            throw new RuntimeException("Complaint not found");
        }
        complaintRepository.deleteById(complaintId);
    }

    @Override
    public ComplaintShortDTO getComplaintById(Long id) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow();

        return complaintMapper.toDTO(complaint);
    }
}
