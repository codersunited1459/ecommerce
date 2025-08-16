package com.user.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.userservice.dto.UserRequestDTO;
import com.user.userservice.dto.UserResponseDTO;
import com.user.userservice.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
		UserResponseDTO response = userService.createUser(request);
		return ResponseEntity.ok(response);
	}
}
