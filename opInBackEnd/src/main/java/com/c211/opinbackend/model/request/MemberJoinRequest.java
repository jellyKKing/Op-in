package com.c211.opinbackend.model.request;

import lombok.Getter;

@Getter
public class MemberJoinRequest {

	private String nickname;

	private String email;
	private String password;
}
