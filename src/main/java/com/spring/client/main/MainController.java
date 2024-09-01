package com.spring.client.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.admin.space.domain.Space;
import com.spring.admin.space.service.SpaceService;

import lombok.Setter;

@Controller
public class MainController {
	
	@Setter(onMethod_ = @Autowired)
	private SpaceService spaceService;

	// 매핑 - 메인 페이지
	@GetMapping("/")
	public String mainClient() {
		return "client/main";
	}
	
	// 매핑 - 공간 리스트
	@GetMapping("/space")
	public String spaceList(Model model) {
		List<Space> spaceList= spaceService.spaceList(new Space());
		model.addAttribute("spaceList", spaceList);
		
		return "client/space/spaceList";
	}
	
}
