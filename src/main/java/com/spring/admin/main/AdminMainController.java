package com.spring.admin.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 관리자
@Controller
public class AdminMainController {
	
	@GetMapping("/admin/main")
	public String mainAdmin() {
		return "admin/adminMain";
	}
}
