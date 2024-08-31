package com.spring.client.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.client.auth.service.UserAuthService;
import com.spring.client.domain.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private final UserAuthService userAuthService;

    // 회원가입 페이지를 반환
    @GetMapping("/signupForm")
    public String signupForm(Member member) {
        return "client/auth/signupForm";
    }

    // 아이디 중복 체크
    @PostMapping("/checkId")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkId(@RequestBody Map<String, String> request) {
        String memberId = request.get("memberId");
        boolean available = userAuthService.isIdAvailable(memberId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", available);
        return ResponseEntity.ok(response);
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(Member member) {
        userAuthService.saveMember(member);
        return "redirect:/auth/signupSuccess";
    }

    // 회원가입 성공 페이지를 반환
    @GetMapping("/signupSuccess")
    public String signupSuccess() {
        return "client/auth/signupSuccess";
    }

    // 로그인 페이지를 반환
    @GetMapping("/login")
    public String userLoginForm() {
        return "client/auth/userLoginForm";
    }

    // 로그인 처리
    @PostMapping("/userLogin")
    @ResponseBody
    public Map<String, String> userLogin(@RequestBody Member loginRequest, HttpSession session) {
        Member member = userAuthService.userLogin(loginRequest.getMemberId(), loginRequest.getMemberPassword());
        Map<String, String> response = new HashMap<>();
        if (member != null) {
            session.setAttribute("loggedInUser", member);
            response.put("message", "Login successful");
        } else {
            response.put("message", "Invalid credentials");
        }
        return response;
    }

    // 로그인 상태 확인
    @GetMapping("/isLoggedIn")
    @ResponseBody
    public ResponseEntity<Boolean> isLoggedIn(HttpSession session) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        boolean loggedIn = loggedInUser != null;
        return ResponseEntity.ok(loggedIn);
    }

    // 로그인한 사용자 정보 반환
    @GetMapping("/getUserInfo")
    @ResponseBody
    public ResponseEntity<Member> getUserInfo(HttpSession session) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }

    // 이메일로 인증 코드 발송
    @PostMapping("/sendVerificationCode")
    @ResponseBody
    public ResponseEntity<Map<String, String>> sendVerificationCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Map<String, String> response = new HashMap<>();
        try {
            userAuthService.sendVerificationCode(email);
            response.put("message", "Verification code sent to email");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "Failed to send verification code");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 인증 코드 검증
    @PostMapping("/verifyCode")
    @ResponseBody
    public ResponseEntity<Map<String, String>> verifyCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        boolean valid = userAuthService.verifyCode(email, code);
        Map<String, String> response = new HashMap<>();
        if (valid) {
            response.put("message", "Verification code valid");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid or expired verification code");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // 사용자 아이디/비밀번호 찾기 폼
    @GetMapping("/findIdPwdForm")
    public String findIdForm() {
        return "client/auth/findIdPwdForm";
    }

    // 아이디 찾기
    @PostMapping("/findId")
    @ResponseBody
    public Map<String, String> findId(@RequestBody Map<String, String> request, HttpSession session) {
        String name = request.get("name");
        String email = request.get("email");
        
        String memberId = userAuthService.findIdByNameAndEmail(name, email);
        Map<String, String> response = new HashMap<>();
        
        if (memberId != null) {
            session.setAttribute("memberId", memberId);
            response.put("status", "success");
        } else {
            response.put("status", "error");
            response.put("message", "Member not found");
        }
        
        return response;
    }


    // 아이디 찾기 결과 페이지
    @GetMapping("/findIdResult")
    public String findIdResult(HttpSession session, Model model) {
        String memberId = (String) session.getAttribute("memberId");
        if (memberId != null) {
            // 세션에서 memberId를 읽어서 모델에 추가
            model.addAttribute("memberId", memberId);
            session.removeAttribute("memberId");
            return "client/auth/findIdResult"; // 결과 페이지로 이동
        } else {
            // 세션에 memberId가 없으면 오류 페이지로 이동하거나 적절한 처리를 함
            return "redirect:/auth/findIdPwdForm";
        }
    }
    
    // 임시비밀번호 설정
    @PostMapping("/resetPassword")
    @ResponseBody
    public Map<String, String> resetPassword(@RequestBody Map<String, String> request) {
        String memberId = request.get("memberId");
        String email = request.get("email");

        Map<String, String> response = new HashMap<>();
        boolean result = userAuthService.resetPassword(memberId, email);

        if (result) {
            response.put("message", "제공된 이메일로 임시 비밀번호가 전송되었습니다.");
        } else {
            response.put("message", "비밀번호 재설정에 실패했습니다. 정보를 확인하고 다시 시도해 주세요.");
        }
        return response;
    }
    
    // 임시비밀번호 결과 확인 페이지
    @GetMapping("/findPwdResult")
    public String findPwdResult() {
        return "client/auth/findPwdResult";
    }
    
}
