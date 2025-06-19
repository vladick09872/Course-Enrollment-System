package com.example.Keycloak.mapper;

import com.example.Keycloak.DTO.ComplaintShortDTO;
import com.example.Keycloak.model.Complaint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {
    ComplaintShortDTO toDTO(Complaint complaint);
}
