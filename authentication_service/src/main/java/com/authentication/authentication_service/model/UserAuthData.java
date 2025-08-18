package com.authentication.authentication_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "user_auth_data", uniqueConstraints = { @UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "mobile") })
public class UserAuthData {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime CreatedDate;
    @UpdateTimestamp
    @Column(nullable = false)
    private Date modifiedDate;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String mobile;
    private String password;
    private String role;

    private UUID authUserId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        CreatedDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public UUID getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(UUID authUserId) {
		this.authUserId = authUserId;
	}

	@Override
	public String toString() {
		return "UserAuthData [id=" + id + ", CreatedDate=" + CreatedDate + ", modifiedDate=" + modifiedDate + ", email="
				+ email + ", mobile=" + mobile + ", password=" + password + ", role=" + role + ", authUserId="
				+ authUserId + "]";
	}
}
