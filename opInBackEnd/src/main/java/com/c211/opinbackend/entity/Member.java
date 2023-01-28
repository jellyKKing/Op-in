package com.c211.opinbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MEMBER_SEQ")
	private Long seq;

	@Column(name = "NICKNAME")
	private String nickname;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PROVIDER")
	@Enumerated(EnumType.STRING)
	private ProviderType providerType;
}
