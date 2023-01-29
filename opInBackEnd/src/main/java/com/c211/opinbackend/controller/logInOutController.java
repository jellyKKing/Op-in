package com.c211.opinbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.entity.RoleType;
import com.c211.opinbackend.model.MemberDto;
import com.c211.opinbackend.model.request.MemberJoinRequest;
import com.c211.opinbackend.model.request.MemberRequest;
import com.c211.opinbackend.repository.MemberRepository;
import com.c211.opinbackend.service.CustomUserDetailService;
import com.c211.opinbackend.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
public class logInOutController {
	private final MemberRepository memberRepository;
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	private final CustomUserDetailService customUserDetailService;

	@PostMapping("/join")
	public ResponseEntity<?> signup(@RequestBody MemberJoinRequest memberJoinRequest) {

		MemberDto memberDto = MemberDto.builder()
			.email(memberJoinRequest.getEmail())
			.password(passwordEncoder.encode(memberJoinRequest.getPassword()))
			.nickname(memberJoinRequest.getNickname())
			.roleType(RoleType.ROLE_USER)
			.build();

		if (!memberDto.isAnyNull() && !memberDto.isNotBlink()) {
			memberService.signUp(memberDto);
			// TODO: 2023/01/28 에러 처리 어드바이저 필요합니다
			// TODO: 2023/01/28 중복 아이디 검사 필요
		} else {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(memberDto);
	}

	@PostMapping("/login")
	public ResponseEntity<?> signIn(@RequestBody MemberRequest memberRequest) {
		log.info(memberRequest.getEmail());
		log.info(memberRequest.getPassword());
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info(name);

		// TODO: 2023/01/29 비밀번호 확인 필요
		// 토큰 없이는 못해.. jwt 없이는 못하네?
		return ResponseEntity.ok(memberRequest);
	}
}
