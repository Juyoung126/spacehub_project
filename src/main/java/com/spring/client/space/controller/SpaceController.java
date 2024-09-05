package com.spring.client.space.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.admin.space.domain.Space;
import com.spring.admin.space.service.SpaceService;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Controller
@RequestMapping("/space/*")
@RequiredArgsConstructor
public class SpaceController {
	
	@Setter(onMethod_ = @Autowired)
	private SpaceService spaceService;

	// 공간 리스트 조회
	@GetMapping("/spaceList")
	public String spaceList(Space space, Model model) {
		List<Space> spaceList= spaceService.spaceList(space);
		model.addAttribute("spaceList", spaceList);
		
		return "client/space/spaceList";
	}
	
	// 공간 상세 조회
	@GetMapping("/{spNo}")
	public String spaceDetail(@PathVariable Long spNo, Space space, Model model) {
		space.setSpNo(spNo);
		Space detail = spaceService.spaceDetail(space);
		model.addAttribute("detail", detail);
		
		String newLine = System.getProperty("line.separator").toString();
		model.addAttribute("newLine", newLine);
		
		return "client/space/spaceDetail";
	}
}
