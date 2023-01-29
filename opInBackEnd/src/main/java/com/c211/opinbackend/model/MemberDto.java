package com.c211.opinbackend.model;

import java.io.Serializable;

import com.c211.opinbackend.entity.ProviderType;
import com.c211.opinbackend.entity.RoleType;

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
	private RoleType roleType;
	public boolean isNotBlink(){
		return nickname.equals("") || email.equals("") || password.equals("");
	}
	public boolean isAnyNull(){
		return nickname == null || email == null || password == null;
	}

}
