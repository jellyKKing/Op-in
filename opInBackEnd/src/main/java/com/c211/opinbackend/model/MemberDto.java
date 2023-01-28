package com.c211.opinbackend.model;

import java.io.Serializable;

import com.c211.opinbackend.entity.ProviderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto implements Serializable {
	private String nickname;

	private String email;
	private String password;

	private ProviderType providerType;
}
