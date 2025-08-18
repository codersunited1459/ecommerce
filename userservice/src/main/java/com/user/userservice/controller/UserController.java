package com.user.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.userservice.dto.ApiResponse;
import com.user.userservice.dto.UserRequestDTO;
import com.user.userservice.dto.UserResponseDTO;
import com.user.userservice.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@RequestBody UserRequestDTO request) {
		
		// creating user
		UserResponseDTO response = userService.createUser(request);

		ApiResponse<UserResponseDTO> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(),
				"User created successfully", null // return actual UserAuthData
		);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

	}

}
