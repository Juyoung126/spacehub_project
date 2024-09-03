package com.spring.admin.memberManagement.service;

import java.util.List;
import java.util.Optional;

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
        return memberManagementRepository.findAllByOrderByMemberNoAsc(); // findAll()은 전체 목록을 반환
    }
    
    @Override
    public Member memberDetail(Member member) {
    	Optional<Member> memberOptional = memberManagementRepository.findById(member.getMemberNo());
    	Member detail =  memberOptional.get();
    	return detail;
    }
}

