package com.authentication.authentication_service.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class AuthRegisterResponeDTO {

    private String userName;
    private UUID authUserId;
     
	public AuthRegisterResponeDTO(String userName, UUID authUserId) {
		super();
		this.userName = userName;
		this.authUserId = authUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public UUID getAuthUserId() {
		return authUserId;
	}
	public void setAuthUserId(UUID authUserId) {
		this.authUserId = authUserId;
	}
	@Override
	public String toString() {
		return "UserServiceDTO [userName=" + userName + ", authUserId=" + authUserId + "]";
	}

}
