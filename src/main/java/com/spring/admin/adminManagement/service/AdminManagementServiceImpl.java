package com.spring.admin.adminManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.admin.adminManagement.repository.AdminManagementRepository;
import com.spring.admin.domain.Admin;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminManagementServiceImpl implements AdminManagementService {
	private final AdminManagementRepository adminManagementRepository;
    
    @Override
    public List<Admin> adminList(Admin admin) {
        return adminManagementRepository.findAllByOrderByAdmNoAsc(); // 전체 목록 반환
    }
    
    @Override
    public Admin getAdminByAdmNo(Long admNo){
        return adminManagementRepository.findByAdmNo(admNo); // memberNo로 조회
    }
    
    @Override
    public void saveAdmin(Admin admin) {
    	// TODO Auto-generated method stub
    	adminManagementRepository.save(admin);
    }
}
