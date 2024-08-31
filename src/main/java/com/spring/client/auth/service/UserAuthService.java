package com.spring.client.auth.service;

import com.spring.client.domain.Member;

public interface UserAuthService {
    void saveMember(Member member);
    boolean isIdAvailable(String memberId);
    Member userLogin(String memberId, String memberPassword);
    void sendVerificationCode(String email);
    boolean verifyCode(String email, String code); // 인증번호를 위한 파라미터 추가
}
