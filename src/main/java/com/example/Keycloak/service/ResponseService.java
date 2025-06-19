package com.example.Keycloak.service;

import com.example.Keycloak.DTO.ResponseShortDTO;

import java.util.List;

public interface ResponseService {
    ResponseShortDTO createResponse(ResponseShortDTO responseDTO, String officialUsername);

    List<ResponseShortDTO> getResponsesForComplaint(Long complaintId);
}
