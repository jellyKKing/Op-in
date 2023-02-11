package com.c211.opinbackend.repo.service.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.persistence.entity.TitleContent;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoPostRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;
import com.c211.opinbackend.repo.model.requeset.RequestUpdatePost;
import com.c211.opinbackend.repo.model.response.RepoPostDetailResponse;
import com.c211.opinbackend.repo.model.response.RepoPostSaveResponse;
import com.c211.opinbackend.repo.model.response.RepoPostSimpleResponse;
import com.c211.opinbackend.repo.service.mapper.RepoPostMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RepositoryPostServiceImpl implements RepositoryPostService {

	private final MemberRepository memberRepository;
	private final RepoRepository repoRepository;
	private final RepoPostRepository repoPostRepository;

	@Override
	@Transactional
	public RepoPostSaveResponse createPostToRepository(CreatePostRequest createPostRequest, String memberEmail) {
		// 등록할 래포지토리를 찾고

		Long repositoryId = createPostRequest.getRepositoryId();
		Repository repository = repoRepository.findById(repositoryId).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION)
		);
		// 등록하는 맴버를 찾는다.
		Member member = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		// 포스트를 등록한다.
		try {
			RepositoryPost repositoryPost = RepositoryPost.builder()
				.repository(repository)
				.member(member)
				.titleContent(TitleContent.builder()
					.content(createPostRequest.getContent())
					.title(createPostRequest.getTitle())
					.build())
				.mergeFL(false)
				.commentsList(new ArrayList<>())
				.likeList(new ArrayList<>())
				.date(LocalDateTime.now())
				.closeState(false)
				.build();
			repositoryPost.createPostToRepo(repository);
			RepositoryPost save = repoPostRepository.save(repositoryPost);
			RepoPostSaveResponse repoPostSaveResponse = RepoPostMapper.toSaveResponse(save);
			return repoPostSaveResponse;
		} catch (Exception exception) {
			return null;
		}
	}

	@Override
	@Transactional
	public List<RepoPostSimpleResponse> getAllPost() {

		List<RepositoryPost> repositoryPostList = repoPostRepository.findAll();
		List<RepoPostSimpleResponse> mappingResult = new ArrayList<>();
		for (RepositoryPost post : repositoryPostList) {
			mappingResult.add(RepoPostMapper.toSimpleResponse(post));
		}
		return mappingResult;
	}

	@Override
	@Transactional
	public List<RepoPostSimpleResponse> getRepoAllPostList(Long repoId) {
		Repository repository = repoRepository.findById(repoId).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION)
		);
		List<RepositoryPost> repositoryPostList = repoPostRepository.findByRepositoryId(repository.getId());
		List<RepoPostSimpleResponse> mappingResult = new ArrayList<>();
		for (RepositoryPost post : repositoryPostList) {
			mappingResult.add(RepoPostMapper.toSimpleResponse(post));
		}
		return mappingResult;
	}

	@Override
	@Transactional
	public RepoPostDetailResponse getRepoDetail(Long postId) {
		RepositoryPost repositoryPost = repoPostRepository.findById(postId).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_POST_EXIST_EXCEPTION));
		return RepoPostMapper.toDetailResponse(repositoryPost);
	}

	@Override
	@Transactional
	public Boolean deleteRepo(Long postId) {
		repoPostRepository.deleteById(postId);
		return true;
	}

	@Override
	@Transactional
	public Boolean update(RequestUpdatePost post) {
		RepositoryPost repositoryPost = repoPostRepository.findById(post.getPostId()).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_POST_EXIST_EXCEPTION)
		);
		repositoryPost.fetchTile(post.getPostTile());
		repositoryPost.fetchContent(post.getPostContent());
		return true;
	}

	@Override
	@Transactional
	public List<RepoPostSimpleResponse> getMembersRepoPost(String nickName) {
		List<RepositoryPost> byMemberId = repoPostRepository.findByMember_Nickname(nickName);
		log.info(String.valueOf(byMemberId.size()));
		List<RepoPostSimpleResponse> resultMapper = new ArrayList<>();
		for (RepositoryPost post : byMemberId) {
			RepoPostSimpleResponse repoPostSimpleResponse = RepoPostMapper.toSimpleResponse(post);
			resultMapper.add(repoPostSimpleResponse);
		}
		return resultMapper;
	}
}
