package com.c211.opinbackend.repo.model.requeset;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestComment {
	private String comment;
	private Long qnaId;
}
