package com.spring.client.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.client.auth.service.UserAuthService;
import com.spring.client.domain.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPage")
public class UserMypageController {

    private final UserAuthService userAuthService;

    @GetMapping
    public String myPage(HttpSession session, Model model) {
        String memberId = (String) session.getAttribute("loggedInUser");
        if (memberId != null) {
            Member loggedInUser = userAuthService.getMemberById(memberId); // memberId로 사용자 정보 조회
            model.addAttribute("member", loggedInUser);
            model.addAttribute("isLoggedIn", true); // 로그인 상태를 모델에 추가
            return "client/mypage/userProfile";
        } else {
            model.addAttribute("isLoggedIn", false); // 로그인 상태를 모델에 추가
            return "client/mypage/userProfile"; // 로그인 여부를 클라이언트에서 처리
        }
    }
}
