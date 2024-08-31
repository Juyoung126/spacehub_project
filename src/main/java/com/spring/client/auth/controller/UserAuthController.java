package com.spring.client.auth.controller;

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

import com.spring.client.auth.service.UserAuthService;
import com.spring.client.domain.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {

    @Autowired
    private final UserAuthService userAuthService;

    @GetMapping("/signupForm")
    public String signupForm(Member member) {
        return "client/auth/signupForm"; 
    }

    @PostMapping("/checkId")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkId(@RequestBody Map<String, String> request) {
        String memberId = request.get("memberId");
        boolean available = userAuthService.isIdAvailable(memberId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", available);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public String signup(Member member) {
        userAuthService.saveMember(member); 
        return "redirect:/auth/signupSuccess";
    }

    @GetMapping("/signupSuccess")
    public String signupSuccess() {
        return "client/auth/signupSuccess";
    }

    @GetMapping("/login")
    public String userLoginForm() {
        return "client/auth/userLoginForm"; 
    }

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

    @GetMapping("/isLoggedIn")
    @ResponseBody
    public ResponseEntity<Boolean> isLoggedIn(HttpSession session) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        boolean loggedIn = loggedInUser != null;
        return ResponseEntity.ok(loggedIn);
    }

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

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }

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
}
