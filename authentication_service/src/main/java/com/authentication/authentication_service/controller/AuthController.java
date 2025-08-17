package com.authentication.authentication_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.authentication_service.dto.UserDTO;
import com.authentication.authentication_service.dto.UserServiceDTO;
import com.authentication.authentication_service.factory.AuthProviderFactory;
import com.authentication.authentication_service.feign.UserServiceClient;
import com.authentication.authentication_service.security.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
	

    @Autowired
    private  AuthProviderFactory providerFactory;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserServiceClient userServiceClient;

    @PostMapping("/register")
    public  ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        try {
            UserServiceDTO userService = providerFactory.getProvider().register(userDTO);
            userServiceClient.sendUserData(userService);

            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String mobile,@RequestParam String password) {
        return providerFactory.getProvider().login(mobile, password);
    }

    @GetMapping("checkTokenData/{token}")
    public ResponseEntity checkTokenData(@PathVariable String token){

        return ResponseEntity.ok(jwtUtil.extractAllClaims(token));
    }
}

