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
@RequestMapping("/admin/space/*")
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
