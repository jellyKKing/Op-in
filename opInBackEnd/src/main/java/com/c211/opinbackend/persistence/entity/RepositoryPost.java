package com.c211.opinbackend.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.Getter;

@Entity
@Getter

public class RepositoryPost {
	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_POST_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@Embedded
	private TitleContent titleContent;

	@NotNull
	private Boolean mergeFL;
	@NotNull
	private LocalDateTime date;
	@NotNull
	private Boolean closeState;

	@NotNull
	private String imageUrl;
}