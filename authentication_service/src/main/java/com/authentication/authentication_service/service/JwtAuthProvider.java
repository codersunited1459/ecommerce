package com.authentication.authentication_service.service;


import java.util.UUID;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.authentication.authentication_service.dto.UserDTO;
import com.authentication.authentication_service.globalException.DuplicateResourceException;
import com.authentication.authentication_service.dto.AuthRegisterResponeDTO;
import com.authentication.authentication_service.model.UserAuthData;
import com.authentication.authentication_service.repository.UserAuthDataRepository;
import com.authentication.authentication_service.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
    public AuthRegisterResponeDTO register(UserDTO user) {
    	
    	if (userRepo.existsByMobile(user.getMobile())) {
            throw new DuplicateResourceException("USER already exists: " + user.getMobile());
        }
        UUID uuid = UUID.randomUUID();
        UserAuthData userAuthData=new UserAuthData();
        userAuthData.setMobile(user.getMobile());
        userAuthData.setEmail(user.getEmail());
        userAuthData.setPassword(passwordEncoder.encode(user.getPassword()));
        userAuthData.setRole("USER");
        userAuthData.setAuthUserId(uuid);
        userRepo.save(userAuthData);
        return new AuthRegisterResponeDTO(user.getUserName(),uuid);
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

