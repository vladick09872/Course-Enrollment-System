package com.example.Keycloak.service.impl;

import com.example.Keycloak.DTO.ResponseShortDTO;
import com.example.Keycloak.mapper.ResponseMapper;
import com.example.Keycloak.model.Response;
import com.example.Keycloak.model.User;
import com.example.Keycloak.repository.ComplaintRepository;
import com.example.Keycloak.repository.ResponseRepository;
import com.example.Keycloak.repository.UserRepository;
import com.example.Keycloak.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {
    private final ResponseMapper responseMapper;
    private final ComplaintRepository complaintRepository;
    private final ResponseRepository repository;
    private final UserRepository userRepository;

    @Override
    public ResponseShortDTO createResponse(ResponseShortDTO responseDTO, String officialUsername) {
        User official = userRepository.findByUsername(officialUsername)
                .orElseThrow();

        Response response = responseMapper.toEntity(responseDTO);

        response.setResponder(official);

        Response savedResponse = repository.save(response);

        return responseMapper.toDTO(savedResponse);
    }

    @Override
    public List<ResponseShortDTO> getResponsesForComplaint(Long complaintId) {
        List<Response> response = repository.findByComplaintId(complaintId);

        return response.stream()
                .map(responseMapper::toDTO)
                .toList();
    }
}

