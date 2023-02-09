package com.c211.opinbackend.batch.step;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.batch.dto.github.CommitDto;
import com.c211.opinbackend.batch.dto.github.PullRequestDto;
import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.batch.dto.github.UserDto;
import com.c211.opinbackend.constant.GitHub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class Action {

	public static UserDto getMemberInfo(String githubUserName) {
		return WebClient.create()
			.get()
			.uri(GitHub.getUserInfoUrl(githubUserName))
			.retrieve()
			.bodyToMono(UserDto.class).block();
	}

	public static RepositoryDto[] getMemberRepository(String githubToken, String githubUserName) {
		return WebClient.create()
			.get()
			.uri(GitHub.getUserRepoUrl(githubUserName))
			.headers(header -> {
				if (githubToken != null && !githubToken.isEmpty()) {
					header.setBearerAuth(githubToken);
				}
			}).retrieve().bodyToMono(RepositoryDto[].class).block();
	}

	public static Map<String, Long> getRepositoryLanguages(String repositoryFullName) {
		return WebClient.create()
			.get()
			.uri(
				GitHub.getPublicRepositoryLanguageUrl(repositoryFullName)
			).retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Long>>() {
			}).block();
	}

	public static Map<String, Long> getRepositoryLanguages(String repositoryName, String githubUserName) {
		return WebClient.create()
			.get()
			.uri(
				GitHub.getPublicRepositoryLanguageUrl(
					repositoryName, githubUserName)
			).retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Long>>() {
			}).block();
	}

	public static CommitDto[] getRepositoryCommits(String repositoryFullName) {
		return WebClient.create()
			.get()
			.uri(GitHub.getPublicRepositoryCommitUrl(repositoryFullName))
			.retrieve().bodyToMono(CommitDto[].class).block();
	}

	public static PullRequestDto[] getRepositoryPulls(String repositoryFullName) {
		return WebClient.create()
			.get()
			.uri(GitHub.getPublicRepositoryPullsUrl(repositoryFullName))
			.retrieve().bodyToMono(PullRequestDto[].class).block();
	}
}