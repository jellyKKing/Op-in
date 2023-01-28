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
public class SecurityConfig {

		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
				// rest 서버이기 때문에 인증정보는 stateLess 하다 그렇기데 csrf 토큰 이 필요없다.
				.authorizeRequests().antMatchers("/api/hello").permitAll()
				/**
				 * httpServletRquest를 사용하느 오쳥에 대한 접근 제한자를 설정하겠다
				 */
				.anyRequest().authenticated();// 나머지 요청은 인증을 받아야한다.
			return http.build();
		}

}