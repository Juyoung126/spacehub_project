package com.spring.admin.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.admin.auth.repository.AdminAuthRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class AdminAuthRepositoryTest {

	@Setter(onMethod_ = @Autowired)
	private AdminAuthRepository adminAuthRepository;
	
	/* 로그인 처리 확인 */
}
