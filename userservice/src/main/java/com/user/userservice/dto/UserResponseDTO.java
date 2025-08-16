package com.user.userservice.dto;

import java.util.UUID;

public class UserResponseDTO {
	private Long id;
	private UUID authUserId;
	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(UUID authUserId) {
		this.authUserId = authUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
