package com.authentication.authentication_service.service;

import com.authentication.authentication_service.dto.UserDTO;
import com.authentication.authentication_service.dto.AuthRegisterResponeDTO;

public interface AuthProvider {

    AuthRegisterResponeDTO register(UserDTO userDTO);
    String login(String username, String password);
}
