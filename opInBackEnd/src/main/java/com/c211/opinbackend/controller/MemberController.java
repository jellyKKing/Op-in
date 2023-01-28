package com.c211.opinbackend.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.c211.opinbackend.constant.GitHub;
import com.c211.opinbackend.entity.Member;
import com.c211.opinbackend.model.MemberDto;
import com.c211.opinbackend.model.request.OAuthAccessTokenRequest;
import com.c211.opinbackend.model.request.MemberJoinRequest;
import com.c211.opinbackend.model.request.MemberRequest;
import com.c211.opinbackend.model.response.OAuthAccessTokenResponse;
import com.c211.opinbackend.model.response.OAuthUserInfoResponse;
import com.c211.opinbackend.repository.MemberRepository;
import com.c211.opinbackend.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/auth")
public class MemberController {

	private static final RestTemplate restTemplate = new RestTemplate();
	private final String clientId;
	private final String clientSecret;

	MemberService userService;
	private final MemberRepository memberRepository;

	@Autowired
	public MemberController(MemberService userService,
		MemberRepository memberRepository,
		@Value("${security.oauth.github.client-id}") String clientId,
		@Value("${security.oauth.github.client-secret}") String clientSecret
	) {
		this.userService = userService;
		this.memberRepository = memberRepository;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody MemberRequest request) {
		Member member = userService.login(request.getEmail(), request.getPassword());
		if (member == null) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.ok(member);
	}

	@PostMapping("/signup")
	public ResponseEntity<Boolean> signUp(@RequestBody MemberJoinRequest request) {
		MemberDto joinUser = MemberDto.builder().email(request.getEmail()).password(request.getPassword())
			.nickname(request.getNickname()).build();

		userService.signUp(joinUser);

		return ResponseEntity.ok(true);
	}

	@GetMapping("/github")
	public void redirectGithub(HttpServletResponse response) throws IOException {
		response.sendRedirect(GitHub.AUTHORIZE_URL + "?client_id=" + clientId);
	}

	@GetMapping("/github/callback")
	public ResponseEntity<MemberDto> getUserInfo(@RequestParam String code) {
		String accessToken = getAccessToken(code);
		MemberDto user = getUserName(accessToken);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	private String getAccessToken(String code) {
		return restTemplate.postForEntity(GitHub.ACCESS_TOKEN_URL,
			new OAuthAccessTokenRequest(clientId, clientSecret, code),
			OAuthAccessTokenResponse.class
		).getBody().getAccessToken();
	}

	private MemberDto getUserName(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		HttpEntity<Void> request = new HttpEntity<>(headers);

		return restTemplate
			.exchange(GitHub.USER_INFO_URL,
				HttpMethod.GET,
				request,
				OAuthUserInfoResponse.class
			).getBody().toUser();
	}

}
