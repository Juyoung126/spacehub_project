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

@Controller
@RequestMapping("/admin/space")
@RequiredArgsConstructor
public class AdminSpaceController {

	@Autowired
    private final SpaceService spaceService;

    // 공간 리스트 조회
    @GetMapping("/spaceList")
    public String spaceList(Model model) {
        List<Space> spaceList = spaceService.spaceList(new Space());
        model.addAttribute("spaceList", spaceList);
        return "admin/space/adminSpaceList";
    }

    // 공간 등록 폼
    @GetMapping("/insertForm")
    public String insertForm(Model model) {
        model.addAttribute("space", new Space());
        return "admin/space/adminInsertForm";
    }

    // 공간 등록 처리
    @PostMapping("/spaceInsert")
    public String spaceInsert(Space space) {
        spaceService.spaceInsert(space);
        return "redirect:/admin/space/adminSpaceList";
    }

    // 공간 상세 조회
    @GetMapping("/{spNo}")
    public String spaceDetail(@PathVariable Long spNo, Model model) {
        Space space = spaceService.getSpace(spNo);
        model.addAttribute("detail", space);
        return "admin/space/spaceDetail";
    }

    // 공간 수정 폼
    @GetMapping("/updateForm/{spNo}")
    public String updateForm(@PathVariable Long spNo, Model model) {
        Space space = spaceService.getSpace(spNo);
        model.addAttribute("updateData", space);
        return "admin/space/updateForm";
    }

    // 공간 수정 처리
    @PostMapping("/spaceUpdate")
    public String spaceUpdate(Space space) {
        spaceService.spaceUpdate(space);
        return "redirect:/admin/space/" + space.getSpNo();
    }

    // 공간 삭제 처리
    @PostMapping("/spaceDelete")
    public String spaceDelete(Space space) {
        spaceService.spaceDelete(space);
        return "redirect:/admin/space/spaceList";
    }
}


/*package com.spring.admin.space.controller;

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
@RequestMapping("/admin/space/*")
@RequiredArgsConstructor
public class SpaceController {
	
	@Setter(onMethod_ = @Autowired)
	private SpaceService spaceService;

	// 공간 리스트 조회
	@GetMapping("/spaceList")
	public String spaceList(Space space, Model model) {
		List<Space> spaceList= spaceService.spaceList(space);
		model.addAttribute("spaceList", spaceList);
		
		return "admin/space/spaceList";
	}
	
	// 공간 등록 폼
	@GetMapping("/insertForm")
	public String insertForm(Space space) {
		return "admin/space/insertForm";
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
		
		return "admin/space/spaceDetail";
	}

	@GetMapping("/updateForm")
	public String updateForm(Space space, Model model) {
		Space updateData = spaceService.getSpace(space.getSpNo());
		model.addAttribute("updateData", updateData);
		return "admin/space/updateForm";
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
}*/
