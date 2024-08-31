package com.spring.admin.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.admin.domain.Admin;


public interface AdminAuthRepository  extends JpaRepository<Admin, Long>{

	// AmdId로 Admin을 조회하는 메서드
	Admin findByAdmId(String amdId);
}
