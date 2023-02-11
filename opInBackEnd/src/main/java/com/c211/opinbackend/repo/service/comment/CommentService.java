package com.c211.opinbackend.repo.service.comment;

import com.c211.opinbackend.repo.model.requeset.RequestComment;
import com.c211.opinbackend.repo.model.requeset.RequestCommentCreateToPost;
import com.c211.opinbackend.repo.model.response.CommentSaveRes;

public interface CommentService {
	CommentSaveRes createCommentToPost(String memberEmail, RequestCommentCreateToPost request);

	Boolean creatQnAComment(RequestComment requestComment, String email);
}
