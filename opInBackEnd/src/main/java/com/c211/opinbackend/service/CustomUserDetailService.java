package com.c211.opinbackend.service;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.entity.Member;
import com.c211.opinbackend.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO: 2023/01/29 이거 우리꺼에 맞게 dto 리턴하게 수정해줬으면합니다
		UserDetails userDetails = memberRepository.findByEmail(email)
			.map(this::createUserDetail)
			.orElseThrow(() -> new UsernameNotFoundException(email + "는 디비에 없는 데이터입니다."));
		log.info(userDetails.getUsername());
		log.info(userDetails.getPassword());
		log.info(userDetails.getAuthorities().toString());
		return userDetails;
	}

	private UserDetails createUserDetail(Member member) {
		String role = member.getRoleType().toString();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
		return new User(String.valueOf(member.getEmail())
			, member.getPassword()
			, Collections.singleton(grantedAuthority));
	}
}
