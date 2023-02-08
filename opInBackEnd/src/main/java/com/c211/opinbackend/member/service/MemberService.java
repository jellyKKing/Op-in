package com.c211.opinbackend.member.service;

import java.util.List;
import java.util.Optional;

import com.c211.opinbackend.auth.model.response.MypageResponse;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.MemberFollow;

public interface MemberService {

	Optional<Member> findByEmail(String email);

	MypageResponse getMemberInfo(String email);

	boolean existEmail(String email);

	boolean existNickname(String nickname);

	boolean isOAuthMember(String email);

	Member modifyNickname(String nickname, String email);

	boolean modifyPassword(String email, String password);

	boolean deleteMember(String email, String password);

	boolean deleteGithubMember(String email);

	Member getMember();

	MemberFollow followMember(String nickname);

	boolean followDeleteMember(String nickname);

	boolean isFollow(String nickname);

	boolean saveTopic(List<String> topics);

	boolean saveTechLanguage(List<String> languages);
}
