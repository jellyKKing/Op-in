package com.c211.opinbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			/**
			 * httpServletRquest를 사용하느 오쳥에 대한 접근 제한자를 설정하겠다
			 */
			.antMatchers("/test/").permitAll() // 이 api 에 대한 인증은 모두 혀용
			.anyRequest().authenticated();// 나머지 요청은 인증을 받아야한다.
		return http.build();
	}
}
