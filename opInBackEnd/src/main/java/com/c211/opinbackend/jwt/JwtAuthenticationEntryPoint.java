package com.c211.opinbackend.jwt;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws
		IOException {
		// 401
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, MemberExceptionEnum.MEMBER_WRONG_EXCEPTION.getErrorMessage());
	}

	@ExceptionHandler (value = {Exception.class})
	public void commence(HttpServletRequest request, HttpServletResponse response,
		Exception exception) throws IOException {
		// 500
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, MemberExceptionEnum.MEMBER_WRONG_EXCEPTION.getErrorMessage());
	}

}
