package com.user.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.userservice.config.UserMapper;
import com.user.userservice.dto.UserRequestDTO;
import com.user.userservice.dto.UserResponseDTO;
import com.user.userservice.entities.User;
import com.user.userservice.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;

	public UserResponseDTO createUser(UserRequestDTO request) {

		if (userRepository.existsByAuthUserId(request.getAuthUserId())) {
			throw new RuntimeException("User already registered!");
		}

		User user = userMapper.toEntity(request);
		User saved = userRepository.save(user);
		return userMapper.toDto(saved);
	}
}
