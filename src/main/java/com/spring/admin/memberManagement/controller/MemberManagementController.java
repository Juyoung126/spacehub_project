package com.spring.admin.memberManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.admin.memberManagement.service.MemberManagementService;
import com.spring.client.domain.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/memberManage")
@RequiredArgsConstructor
public class MemberManagementController {

	@Autowired
	private final MemberManagementService memberManagementService;
	
	/**
	 * 검색 기능 및 페이징 처리 제외
	 * @param member
	 * @return 
	 */
    @GetMapping
    public String memberList(Member member, Model model) {
    	List<Member> memberList = memberManagementService.memberList(member);
    	model.addAttribute("memberList", memberList);
    	
        return "admin/memberManage/memberList"; 
    }    
    
    @GetMapping("/{memberNo}")
    public String memberDetail(@PathVariable Long memberNo, Member member, Model model) {
    	member.setMemberNo(memberNo);
    	Member detail = memberManagementService.memberDetail(member);
    	model.addAttribute("detail", detail);
    	
    	return ("admin/memberManage/memberDetail");
    }
}
