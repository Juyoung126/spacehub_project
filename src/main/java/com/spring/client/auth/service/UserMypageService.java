package com.spring.client.auth.service;

import com.spring.client.domain.Member;

public interface UserMypageService {
    Member getMemberById(String memberId);
    boolean updateMember(Member updatedmember);
    boolean checkPassword(String memberId, String password);
	void nullifyUserData(String memberId);
}