package com.user.userservice.config;

import org.mapstruct.Mapper;

import com.user.userservice.dto.UserRequestDTO;
import com.user.userservice.dto.UserResponseDTO;
import com.user.userservice.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User toEntity(UserRequestDTO dto);

	UserResponseDTO toDto(User entity);
}
