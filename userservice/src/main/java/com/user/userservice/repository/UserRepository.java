package com.user.userservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.userservice.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUsername(String username);

	boolean existsByAuthUserId(UUID authUserId);
}
