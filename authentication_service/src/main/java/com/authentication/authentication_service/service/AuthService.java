package com.authentication.authentication_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.authentication.authentication_service.dto.ApiResponse;
import com.authentication.authentication_service.dto.AuthRegisterResponeDTO;
import com.authentication.authentication_service.dto.UserDTO;
import com.authentication.authentication_service.factory.AuthProviderFactory;
import com.authentication.authentication_service.feign.UserServiceClient;
import com.authentication.authentication_service.globalException.ExternalApiException;
import com.authentication.authentication_service.model.UserAuthData;
import com.authentication.authentication_service.provider.AuthProvider;
import com.authentication.authentication_service.security.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

	@Autowired
	private AuthProviderFactory providerFactory;

	@Autowired
	private UserServiceClient userServiceClient;

	@Autowired
	private JwtUtil jwtUtil;

	@Value("${security.auth.provider:jwt}")
	private String defaultProvider;

	public ResponseEntity<ApiResponse<UserAuthData>> register(UserDTO userDTO) {

		AuthRegisterResponeDTO userService = providerFactory.getProvider(defaultProvider).register(userDTO);
		log.info("Calling the user service with {}", userService);

		ResponseEntity<UserAuthData> serviceResponse = userServiceClient.sendUserData(userService);
		log.info("Response from user service : {}", serviceResponse);

		if (serviceResponse.getStatusCode().is2xxSuccessful()) {
			ApiResponse<UserAuthData> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(),
					"User created successfully", null);
			return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
		} else {
			throw new ExternalApiException("Failed while posting to add user API for mobile : " + userDTO.getMobile());
		}

	}

	public String login(String mobile, String password) {
		return providerFactory.getProvider(defaultProvider).login(mobile, password);

	}

	public ResponseEntity checkTokenData(String token) {
		return ResponseEntity.ok(jwtUtil.extractAllClaims(token));
	}

}
