package com.spring.admin.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.admin.auth.service.AdminAuthService;
import com.spring.admin.domain.Admin;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminAuthController {
	@Autowired
	private AdminAuthService adminAuthService;
	
	/**
     * 로그인 폼을 반환하는 메서드
     * @return 로그인 폼의 뷰 이름
     */
    @GetMapping
    public String adminLoginForm() {
        return "admin/auth/adminLoginForm"; 
    }
    
    
    /**
     * 로그인 처리를 수행하는 메서드
     * @param loginRequest 로그인 정보를 포함한 요청 본문
     * @param session HTTP 세션 객체
     * @return 로그인 결과 메시지를 포함한 응답
     */
    @PostMapping("/adminLogin")
    @ResponseBody
    public Map<String, String> adminLogin(@RequestBody Admin loginRequest, HttpSession session) {
        Admin admin = adminAuthService.adminLogin(loginRequest.getAdmId(), loginRequest.getAdmPasswd());
        
        Map<String, String> response = new HashMap<>();
        if (admin != null) {
            session.setAttribute("loggedInAdmin", admin);
            response.put("message", "Login successful");
        } else {
            response.put("message", "로그인 실패");
        }
        return response;
    }
    
    /**
     * 로그인 상태를 확인하는 메서드
     * @param session HTTP 세션 객체
     * @return 로그인 여부를 포함한 응답
     */
    @GetMapping("/isLoggedIn")
    @ResponseBody
    public ResponseEntity<Boolean> isLoggedIn(HttpSession session) {
        Admin loggedInAdmin = (Admin) session.getAttribute("loggedInAdmin");
        boolean loggedIn = loggedInAdmin != null;
        return ResponseEntity.ok(loggedIn);
    }
    
    /**
     * 로그인된 관리자 정보를 조회하는 메서드
     * @param session HTTP 세션 객체
     * @return 로그인된 관리자 정보를 포함한 응답
     */
    @GetMapping("/getAdminInfo")
    @ResponseBody
    public ResponseEntity<Admin> getUserInfo(HttpSession session) {
        Admin loggedInAdmin = (Admin) session.getAttribute("loggedInAdmin");
        if (loggedInAdmin != null) {
            return ResponseEntity.ok(loggedInAdmin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    /**
     * 로그아웃을 수행하는 메서드
     * @param session HTTP 세션 객체
     * @return 로그인 페이지로의 리디렉션
     */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화 (로그아웃)
        return "redirect:/admin/auth/login"; // 로그아웃 후 로그인 페이지로 리디렉션
    }
}
