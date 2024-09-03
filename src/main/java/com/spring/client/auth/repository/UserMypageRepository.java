package com.spring.client.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.client.domain.Member;

public interface UserMypageRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);
    
    @Modifying
    @Query("UPDATE Member m SET m.memberName = ?2, m.memberPhone = ?3, m.memberPassword = ?4 WHERE m.memberId = ?1")
    int updateMember(String memberId, String memberName, String memberPhone, String memberPassword);
    
}
