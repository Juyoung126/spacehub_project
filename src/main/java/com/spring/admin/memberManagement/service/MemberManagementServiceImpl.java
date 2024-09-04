package com.spring.admin.memberManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.admin.memberManagement.repository.MemberManagementRepository;
import com.spring.client.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberManagementServiceImpl implements MemberManagementService {
    private final MemberManagementRepository memberManagementRepository;
    
    @Override
    public List<Member> memberList(Member member) {
        return memberManagementRepository.findAllByOrderByMemberNoAsc(); // 전체 목록 반환
    }
    
    @Override
    public Member getMemberByMemberNo(Long memberNo) {
        return memberManagementRepository.findByMemberNo(memberNo); // memberNo로 조회
    }
}
