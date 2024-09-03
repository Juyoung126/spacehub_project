package com.spring.admin.memberManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.client.domain.Member;

public interface MemberManagementRepository extends JpaRepository<Member, Long>{
    List<Member> findAllByOrderByMemberNoAsc(); // memberNo를 기준으로 오름차순 정렬
}
