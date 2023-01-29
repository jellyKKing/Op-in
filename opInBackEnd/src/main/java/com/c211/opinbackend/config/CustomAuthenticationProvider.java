package com.c211.opinbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.c211.opinbackend.service.CustomUserDetailService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetails userDetails = customUserDetailService.loadUserByUsername(authentication.getName());
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}
}
