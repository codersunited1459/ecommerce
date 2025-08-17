package com.authentication.authentication_service.service;

import com.authentication.authentication_service.dto.UserDTO;
import com.authentication.authentication_service.dto.UserServiceDTO;

public interface AuthProvider {

    UserServiceDTO register(UserDTO userDTO);
    String login(String username, String password);
}
