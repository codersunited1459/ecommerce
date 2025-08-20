package com.authentication.authentication_service.controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.authentication.authentication_service.dto.ApiResponse;
import com.authentication.authentication_service.dto.UserDTO;
import com.authentication.authentication_service.model.UserAuthData;
import com.authentication.authentication_service.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserAuthData>> register(@RequestBody UserDTO userDTO) throws Exception {
		log.info("Request DTO : {}", objectMapper.writeValueAsString(userDTO));

		return authService.register(userDTO);

	}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String mobile,@RequestParam String password) {
        return ResponseEntity.ok(authService.login(mobile, password));
    }

    @GetMapping("checkTokenData/{token}")
    public ResponseEntity<Map<String, Object>> checkTokenData(@PathVariable String token){

		return authService.checkTokenData(token);
	}
}
