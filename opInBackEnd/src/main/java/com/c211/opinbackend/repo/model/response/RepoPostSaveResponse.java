package com.c211.opinbackend.repo.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RepoPostSaveResponse {
	private Long id;

	private String authorMemberName;
	private String authorMemberAvatar;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createTime;
	private String title;

	private int likeCount;
	private int commentCount;

}
