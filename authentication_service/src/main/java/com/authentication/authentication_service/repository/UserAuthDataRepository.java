package com.authentication.authentication_service.repository;

import com.authentication.authentication_service.model.UserAuthData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthDataRepository  extends JpaRepository<UserAuthData, Long> {

    Optional<UserAuthData> findByMobile(String mobileNumber);
    
    boolean existsByMobile(String mobile);

}