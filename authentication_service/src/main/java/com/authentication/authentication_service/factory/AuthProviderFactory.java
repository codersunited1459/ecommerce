package com.authentication.authentication_service.factory;

import com.authentication.authentication_service.service.AuthProvider;
import com.authentication.authentication_service.service.JwtAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthProviderFactory {
    @Autowired
    private  JwtAuthProvider jwtProvider;
  //  private final KeycloakAuthProvider keycloakProvider;

    @Value("${auth.strategy:jwt}")  // default = jwt
    private String strategy;


    public AuthProvider getProvider() {
//        if ("keycloak".equalsIgnoreCase(strategy)) {
//            return keycloakProvider;
//        }
        return jwtProvider;
    }
}

