package com.spring.admin.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.admin.auth.repository.AdminAuthRepository;
import com.spring.admin.domain.Admin;


@Service
public class AdminAuthServiceImpl implements AdminAuthService {
	
	@Autowired
    private AdminAuthRepository adminAuthRepository;
	
	@Override
	public Admin adminLogin(String admId, String admPassword) {
		Admin admin = adminAuthRepository.findByAdmId(admId);

        // 사용자가 존재하고 비밀번호가 일치하면 해당 사용자 객체를 반환, 그렇지 않으면 null 반환
        if (admin != null && admin.getAdmPasswd().equals(admPassword)) {
            return admin;
        } else {
            return null;
        }
	}
}
