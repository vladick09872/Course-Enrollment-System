package com.example.Keycloak.service;

import com.example.Keycloak.DTO.ComplaintShortDTO;
import com.example.Keycloak.model.Status;

import java.util.List;

public interface ComplaintService {
    ComplaintShortDTO createComplaint(ComplaintShortDTO complaintShortDTO, String username);

    List<ComplaintShortDTO> getComplaintForUser(String username);

    List<ComplaintShortDTO> findAllComplaints();

    ComplaintShortDTO assignComplaint(Long complaintId, String officialUsername);

    ComplaintShortDTO updateComplaint(Long complaintId, Status newStatus, String officialUsername);

    void deleteComplaint(Long complaintId);

    ComplaintShortDTO getComplaintById(Long id);
}
