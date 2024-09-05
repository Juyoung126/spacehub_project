package com.spring.admin.adminManagement.service;

import java.util.List;

import com.spring.admin.domain.Admin;

public interface AdminManagementService {

	List<Admin> adminList(Admin admin);
	Admin getAdminByAdmNo(Long admNo);
	void saveAdmin(Admin admin);

}
