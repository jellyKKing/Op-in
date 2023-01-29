package com.c211.opinbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {

	@GetMapping("/test")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> getUser() {
		log.info("in member");
		// TODO: 2023/01/29 맴버 정보를 가져온다.
		return ResponseEntity.ok("in");
	}
}
