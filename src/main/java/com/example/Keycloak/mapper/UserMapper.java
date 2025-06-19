package com.example.Keycloak.mapper;

import com.example.Keycloak.DTO.UserShortDTO;
import com.example.Keycloak.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserShortDTO toDTO(User user);
}
