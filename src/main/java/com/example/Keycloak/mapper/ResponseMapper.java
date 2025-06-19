package com.example.Keycloak.mapper;

import com.example.Keycloak.DTO.ResponseShortDTO;
import com.example.Keycloak.model.Response;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResponseMapper {
    ResponseShortDTO toDTO(Response response);

    Response toEntity(ResponseShortDTO responseDTO);
}
