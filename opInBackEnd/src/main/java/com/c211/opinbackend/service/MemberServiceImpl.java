package com.c211.opinbackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.entity.Member;
import com.c211.opinbackend.model.MemberDto;
import com.c211.opinbackend.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

	MemberRepository memberRepository;

	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public Member login(String email, String password) {
		Optional<Member> userOptional = memberRepository.findByEmailAndPassword(email, password);
		return userOptional.orElse(null);
	}

	@Override
	public Member signUp(MemberDto memberDto) {

		Member member = Member.builder()
			.email(memberDto.getEmail())
			.password(memberDto.getPassword())
			.nickname(memberDto.getNickname())
			.roleType(memberDto.getRoleType())
			.build();

		return memberRepository.save(member);
	}
}
