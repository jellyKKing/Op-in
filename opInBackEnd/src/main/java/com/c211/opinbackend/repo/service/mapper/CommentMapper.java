package com.c211.opinbackend.repo.service.mapper;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.repo.model.response.CommentDetailReponse;
import com.c211.opinbackend.repo.model.response.CommentSaveRes;

public class CommentMapper {

	public static CommentDetailReponse toDetailCommentDto(Comment comment) {
		return CommentDetailReponse.builder()
			.id(comment.getId())
			.memberAvatarUrl(comment.getMember().getAvatarUrl())
			.memberName(comment.getMember().getNickname())
			.commentContent(comment.getContent())
			.createDate(comment.getCreateDate())
			.updateDate(comment.getUpdateDate())
			.build();

	}

	public static CommentSaveRes toSaveRes(Comment comment) {
		return CommentSaveRes.builder()
			.memberAvatarUrl(comment.getMember().getAvatarUrl())
			.memberName(comment.getMember().getNickname())
			.commentContent(comment.getContent())
			.build();

	}
}
