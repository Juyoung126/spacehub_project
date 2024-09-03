package com.spring.client.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.client.auth.repository.UserMypageRepository;
import com.spring.client.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMypageServiceImpl implements UserMypageService {
	private final UserMypageRepository userMypageRepository;

    @Override
    public Member getMemberById(String memberId) {
        return userMypageRepository.findByMemberId(memberId)
                .orElse(null); // 관리자 정보를 찾지 못하면 null 반환
    }

    @Override
    public boolean checkPassword(String memberId, String rawPassword) {
    	Member member = userMypageRepository.findByMemberId(memberId).orElse(null);
        return member != null && rawPassword.equals(member.getMemberPassword()); // 평문 비밀번호와 비교
    }

    @Transactional
    @Override
    public boolean updateMember(Member updatedMember) {
        if (userMypageRepository.findByMemberId(updatedMember.getMemberId()).isPresent()) {
            int result = userMypageRepository.updateMember(
        		updatedMember.getMemberId(),
        		updatedMember.getMemberName(),
        		updatedMember.getMemberPhone(),
        		updatedMember.getMemberPassword(),
        		updatedMember.getMemberEmail()
            );

            return result > 0; // 업데이트가 성공적으로 수행되었는지 확인
        }
        return false;
    }
    @Transactional
    @Override
    public void nullifyUserData(String memberId) {
    	userMypageRepository.nullifyUserData(memberId);
    }

}
