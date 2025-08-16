package com.user.userservice.dto;

import java.util.UUID;

public class UserRequestDTO {
	private UUID authUserId;
	private String username;

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
