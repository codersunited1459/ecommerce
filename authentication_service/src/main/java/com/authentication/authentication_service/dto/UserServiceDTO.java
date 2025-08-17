package com.authentication.authentication_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class UserServiceDTO {

    private String userName;
    private String UUID;

    public UserServiceDTO(String userName, String UUID) {
        this.userName = userName;
        this.UUID = UUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    @Override
    public String toString() {
        return "UserServiceDTO{" +
                "userName='" + userName + '\'' +
                ", UUID='" + UUID + '\'' +
                '}';
    }
}
