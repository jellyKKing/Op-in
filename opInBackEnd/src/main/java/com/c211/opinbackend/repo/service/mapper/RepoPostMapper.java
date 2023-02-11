package com.c211.opinbackend.repo.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.repo.model.response.CommentDetailReponse;
import com.c211.opinbackend.repo.model.response.RepoPostDetailResponse;
import com.c211.opinbackend.repo.model.response.RepoPostSaveResponse;
import com.c211.opinbackend.repo.model.response.RepoPostSimpleResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepoPostMapper {
	public static RepoPostSimpleResponse toSimpleResponse(RepositoryPost post) {
		return RepoPostSimpleResponse.builder()
			.id(post.getId())
			.authorMemberName(post.getMember().getNickname())
			.authorMemberAvatar(post.getMember().getAvatarUrl())
			.date(post.getDate())
			.title(post.getTitleContent().getTitle())
			.likeCount(post.getLikeList().size())
			.commentCount(post.getCommentsList().size())
			.mergeFl(post.getMergeFL())
			.closeState(post.getCloseState())
			.build();

	}

	public static RepoPostSaveResponse toSaveResponse(RepositoryPost post) {
		return RepoPostSaveResponse.builder()
			.id(post.getId())
			.authorMemberName(post.getMember().getNickname())
			.authorMemberAvatar(post.getMember().getAvatarUrl())
			.createTime(post.getDate())
			.title(post.getTitleContent().getTitle())
			.likeCount(post.getLikeList().size())
			.commentCount(post.getCommentsList().size())
			.build();

	}

	public static RepoPostDetailResponse toDetailResponse(RepositoryPost repositoryPost) {
		List<CommentDetailReponse> detailCommentList = new ArrayList<>();
		for (Comment comment : repositoryPost.getCommentsList()) {
			detailCommentList.add(CommentMapper.toDetailCommentDto(comment));
		}
		return RepoPostDetailResponse.builder()
			.id(repositoryPost.getId())
			.authorMemberName(repositoryPost.getMember().getNickname())
			.authorMemberAvatar(repositoryPost.getMember().getAvatarUrl())
			.date(repositoryPost.getDate())
			.title(repositoryPost.getTitleContent().getTitle())
			.likeCount(repositoryPost.getLikeList().size())
			.commentCount(repositoryPost.getCommentsList().size())
			.commentList(detailCommentList)
			.content(repositoryPost.getTitleContent().getContent())
			.build();
	}
}
