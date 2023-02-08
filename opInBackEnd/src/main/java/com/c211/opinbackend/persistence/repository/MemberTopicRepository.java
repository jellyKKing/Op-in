package com.c211.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.MemberTopic;

@Repository
public interface MemberTopicRepository extends JpaRepository<MemberTopic, Long> {

	List<MemberTopic> findByMember(Member member);
}