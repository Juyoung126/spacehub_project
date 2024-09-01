package com.spring.admin.space.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/space/*")
@RequiredArgsConstructor
public class SpaceController {
	
//	@Setter(onMethod_ = @Autowired)
//	private SpaceService spaceService;

//	@GetMapping("/spaceList")
//	public String spaceList(Model model) {
//		List<Space> spaceList= spaceService.spaceList(new Space());
//		model.addAttribute("spaceList", spaceList);
//		
//		return "client/space/spaceList";
//	}
}
