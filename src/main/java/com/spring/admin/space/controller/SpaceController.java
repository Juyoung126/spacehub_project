package com.spring.admin.space.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/spaceList")
	public String spaceList(Space space, Model model) {
		List<Space> spaceList= spaceService.spaceList(space);
		model.addAttribute("spaceList", spaceList);
		
		return "client/space/spaceList";
	}
	
	@GetMapping("/insertForm")
	public String insertForm(Space space) {
		return "client/space/insertForm";
	}
	
	@PostMapping("/spaceInsert")
	public String spaceInsert(Space space) {
		spaceService.spaceInsert(space);
		return "redirect:/space/spaceList";
	}
	
	@GetMapping("/{spNo}")
	public String spaceDetail(@PathVariable Long spNo, Space space, Model model) {
		space.setSpNo(spNo);
		Space detail = spaceService.spaceDetail(space);
		model.addAttribute("detail", detail);
		
		String newLine = System.getProperty("line.separator").toString();
		model.addAttribute("newLine", newLine);
		
		return "client/space/spaceDetail";
	}

	@GetMapping("/updateForm")
	public String updateForm(Space space, Model model) {
		Space updateData = spaceService.getSpace(space.getSpNo());
		model.addAttribute("updateData", updateData);
		return "client/space/updateForm";
	}
	
	@PostMapping("/spaceUpdate")
	public String spaceUpdate(Space space) {
		spaceService.spaceUpdate(space);
		return "redirect:/space/" + space.getSpNo();
	}
	
	@PostMapping("/spaceDelete")
	public String spaceDelete(Space space) {
		spaceService.spaceDelete(space);
		return "redirect:/space/spaceList";
	}	
}
