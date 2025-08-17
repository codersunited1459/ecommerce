package com.authentication.authentication_service.service;


import com.authentication.authentication_service.dto.UserDTO;
import com.authentication.authentication_service.dto.UserServiceDTO;
import com.authentication.authentication_service.model.UserAuthData;
import com.authentication.authentication_service.repository.UserAuthDataRepository;
import com.authentication.authentication_service.security.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtAuthProvider implements AuthProvider, UserDetailsService {

    private final UserAuthDataRepository userRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthProvider(UserAuthDataRepository userRepo, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserServiceDTO register(UserDTO user) {
        String uuid = UUID.randomUUID().toString();
        UserAuthData userAuthData=new UserAuthData();
        userAuthData.setMobile(user.getMobile());
        userAuthData.setEmail(user.getEmail());
        userAuthData.setPassword(passwordEncoder.encode(user.getPassword()));
        userAuthData.setRole("USER");
        userAuthData.setUUID(uuid);
        userRepo.save(userAuthData);
        return new UserServiceDTO(user.getUserName(),uuid);
    }

    @Override
    public String login(String mobileNumber, String password) {
        UserAuthData user = userRepo.findByMobile(mobileNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(user);
        }
        throw new BadCredentialsException("Invalid credentials");
    }

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) {
        UserAuthData user = userRepo.findByMobile(mobileNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder()
                .username(user.getMobile())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}

