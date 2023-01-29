package com.c211.opinbackend.entity;

import lombok.Getter;

@Getter
public enum RoleType {
	ROLE_USER("ROLE_USER"),
	ROLE_ANONYMOUS("ROLE_ANONYMOUS"),
	ROLE_ADMIN("ROLE_ADMIN");

	String role;

	RoleType(String role) {
		this.role = role;
	}

	public String value() {
		return role;
	}
}
