package com.c211.opinbackend.model.response;

import com.c211.opinbackend.entity.ProviderType;
import com.c211.opinbackend.model.MemberDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthUserInfoResponse {
	private String name;
	private String id;

	@JsonProperty("avatar_url")
	private String avatar;

	private String email;

	public MemberDto toUser() {
		return MemberDto.builder()
			.email(email)
			.nickname(name)
			.providerType(ProviderType.GITHUB)
			.build();
	}
}
