package com.authentication.authentication_service.feign;

import com.authentication.authentication_service.dto.UserServiceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${user.service.url}") // URL of user service
public interface UserServiceClient {

    @PostMapping("/api/users")
    ResponseEntity sendUserData(@RequestBody UserServiceDTO userServiceDTO);

}

