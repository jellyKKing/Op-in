package com.c211.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.MemberFollow;

@Repository
public interface MemberFollowRepository extends JpaRepository<MemberFollow, Long> {
	long countByFromMember(Member member);

	long countByToMember(Member member);

	List<MemberFollow> findByFromMember(Member member);

	void deleteByFromMember(Member member);

	@Override
	void delete(MemberFollow entity);

	Optional<MemberFollow> findByFromMemberAndToMember(Member fromMember, Member toMember);

	boolean existsByFromMemberAndToMember(Member fromMember, Member toMember);

	MemberFollow save(MemberFollow memberFollow);
}
