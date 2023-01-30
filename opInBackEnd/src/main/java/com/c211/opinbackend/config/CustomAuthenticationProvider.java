package com.c211.opinbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.c211.opinbackend.service.CustomUserDetailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String userPwd = (String)authentication.getCredentials();
		log.info(userName);
		UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
		if (!passwordEncoder.matches(userPwd, userDetails.getPassword())) {
			log.info("비밀번호가 틀림");
			throw new BadCredentialsException("비밀번호가 잘못됬습니다!");
		}
		return new UsernamePasswordAuthenticationToken(userName, userPwd, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
