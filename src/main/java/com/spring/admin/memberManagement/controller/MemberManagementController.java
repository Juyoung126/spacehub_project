package com.spring.admin.memberManagement.controller;

import java.time.format.DateTimeFormatter;
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
    
    /* 고객별 상세 페이지 */
    @GetMapping("/{memberNo}")
    public String memberDetail(@PathVariable Long memberNo, Model model) {
        // memberNo를 사용하여 Member 객체를 조회
        Member detail = memberManagementService.getMemberByMemberNo(memberNo);
        
        // Member 객체가 존재하는 경우
        if (detail != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            // 생성일 포맷팅
            String formattedDate_create = detail.getMemberCreatedAt().format(formatter);
            model.addAttribute("formattedDate_create", formattedDate_create);
            
            // 업데이트일 포맷팅
            String formattedDate_update = detail.getMemberUpdateAt() != null ? 
                detail.getMemberUpdateAt().format(formatter) : "";
            model.addAttribute("formattedDate_update", formattedDate_update);
            
            // 모델에 추가 정보 설정
            model.addAttribute("state", true);
            model.addAttribute("member", detail);
            
        } else {
            model.addAttribute("state", false);
            model.addAttribute("message", "회원정보를 찾을 수 없습니다.");
        }
        
        // 결과에 따라 동일한 템플릿을 반환
        return "admin/memberManage/memberDetail";
    }


}
