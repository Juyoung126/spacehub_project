package com.spring.client.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.client.domain.Member;

public interface UserAuthRepository extends JpaRepository<Member, Long> {
	boolean existsByMemberId(String memberId);
	
	// memberId로 Member를 조회하는 메서드
    Member findByMemberId(String memberId);
}
