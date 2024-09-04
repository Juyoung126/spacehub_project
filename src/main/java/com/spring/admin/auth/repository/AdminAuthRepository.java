package com.spring.admin.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.admin.domain.Admin;

public interface AdminAuthRepository extends JpaRepository<Admin, Long> {
	Admin findByAdmId(String admId);
}
