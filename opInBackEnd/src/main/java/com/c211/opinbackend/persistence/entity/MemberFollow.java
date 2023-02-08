package com.c211.opinbackend.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberFollow {
	@Id
	@GeneratedValue
	@Column(name = "MEMBER_FOLLOW_ID")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FROM_MEMBER_ID", referencedColumnName = "MEMBER_ID")
	private Member fromMember;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TO_MEMBER_ID", referencedColumnName = "MEMBER_ID")
	private Member toMember;
}
