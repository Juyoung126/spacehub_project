package com.spring.admin.adminManagement.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.admin.adminManagement.service.AdminManagementService;
import com.spring.admin.domain.Admin;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/adminManage")
@RequiredArgsConstructor
public class AdminManagementController {

	@Autowired
	private final AdminManagementService adminManagementService;
	
	/**
	 * 검색 기능 및 페이징 처리 제외
	 * @param member
	 * @return 
	 */
    @GetMapping
    public String memberList(Admin admin, Model model) {
    	List<Admin> adminList = adminManagementService.adminList(admin);
    	model.addAttribute("adminList", adminList);
    	
        return "admin/adminManage/adminList"; 
    }    
    
    /* 관리자별 상세 페이지 */
    @GetMapping("/{admNo}")
    public String memberDetail(@PathVariable Long admNo, Model model) {
        // memberNo를 사용하여 Member 객체를 조회
    	Admin detail = adminManagementService.getAdminByAdmNo(admNo);
        
        // Member 객체가 존재하는 경우
        if (detail != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            // 생성일 포맷팅
            String formattedDate_create = detail.getAdmCreatedAt().format(formatter);
            model.addAttribute("formattedDate_create", formattedDate_create);
            
            // 업데이트일 포맷팅
            String formattedDate_update = detail.getAdmUpdateAt() != null ? 
                detail.getAdmUpdateAt().format(formatter) : "";
            model.addAttribute("formattedDate_update", formattedDate_update);
            
            // 모델에 추가 정보 설정
            model.addAttribute("state", true);
            model.addAttribute("admin", detail);
            
        } else {
            model.addAttribute("state", false);
            model.addAttribute("message", "관리자정보를 찾을 수 없습니다.");
        }
        
        // 결과에 따라 동일한 템플릿을 반환
        return "admin/adminManage/adminDetail";
    }
    
    @GetMapping("/insertAdminForm")
    public String insertAdminForm(Admin admin) {
    	return "admin/adminManage/insertAdminForm";
    }
    
    @PostMapping("/insertAdmin")
    public String insertAdmin(Admin admin) {
    	adminManagementService.saveAdmin(admin);
        return "redirect:/admin/adminManage";
    }

}
