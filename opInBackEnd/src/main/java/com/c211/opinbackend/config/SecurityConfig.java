package com.c211.opinbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authenticationProvider(authenticationProvider())
			.csrf().disable()// 이거 안하면 포스트매핑이 안된다..
			// rest 서버이기 때문에 인증정보는 stateLess 하다 그렇기데 csrf 토큰 이 필요없다.
			.authorizeRequests()// 인가
			.antMatchers("/login").permitAll()
			.antMatchers("/logout").permitAll()
			.antMatchers("/join").permitAll()
			.anyRequest().authenticated()// 나머지 요청은 인증을 받아야한다.

			// .and().sessionManagement()
			// .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

			.and()
			.httpBasic();

		// .and()
		// .formLogin()
		// .disable();

		return http.build();
	}

}