package com.authentication.authentication_service.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.authentication.authentication_service.provider.AuthProvider;

@Component
public class AuthProviderFactory {

	private final Map<String, AuthProvider> providers = new HashMap<>();

	public AuthProviderFactory(List<AuthProvider> providerList) {
		// Collect all providers, keyed by their type
		for (AuthProvider provider : providerList) {
			providers.put(provider.getType(), provider);
		}
	}

	public AuthProvider getProvider(String providerType) {
		AuthProvider provider = providers.get(providerType);
		if (provider == null) {
			throw new IllegalArgumentException("No provider found for type: " + providerType);
		}
		return provider;
	}
}
