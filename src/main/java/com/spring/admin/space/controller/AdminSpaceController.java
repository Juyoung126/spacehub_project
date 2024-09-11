package com.spring.admin.space.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.admin.auth.service.AdminMypageService;
import com.spring.admin.domain.Admin;
import com.spring.admin.space.domain.Space;
import com.spring.admin.space.domain.SpaceDetail;
import com.spring.admin.space.domain.SpaceImg;
import com.spring.admin.space.service.SpaceDetailService;
import com.spring.admin.space.service.SpaceImgService;
import com.spring.admin.space.service.SpaceService;
import com.spring.common.util.CustomFileUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/space")
@RequiredArgsConstructor
public class AdminSpaceController {

    private final SpaceService spaceService;

	private final SpaceDetailService spaceDetailService;
	
	private final SpaceImgService spaceImgService;
	
	private final CustomFileUtil fileUtil;
	
	private final AdminMypageService adminMypageService;
	
    // 공간 리스트 조회
    @GetMapping("/spaceList")
    public String spaceList(Model model, Space space) {
        List<Space> spaceList = spaceService.spaceList(space);
        List<Space> result = new ArrayList<>();
        
        for(Space spaceData: spaceList) {
        	  SpaceDetail spaceDetail = spaceDetailService.getSpaceDetailBySpaceId(spaceData.getSpNo());
              SpaceImg spaceImg = spaceImgService.getSpaceImgsBySpaceId(spaceDetail.getSpace().getSpNo());
              spaceData.setSpMainImage(spaceImg.getSpImg());
              result.add(spaceData);
        }
        
        model.addAttribute("spaceList", result);
        
        return "admin/space/adminSpaceList";
    }
    
    // 공간 상세 조회
    @GetMapping("/{spNo}")
    public String spaceDetail(@PathVariable Long spNo, Model model) {
        Space space = spaceService.getSpaceById(spNo);
        SpaceDetail spaceDetail = spaceDetailService.getSpaceDetailBySpaceId(spNo);
        SpaceImg spaceImgs = spaceImgService.getSpaceImgsBySpaceId(spNo);
        
        model.addAttribute("space", space);
        model.addAttribute("spaceDetail", spaceDetail);
        model.addAttribute("spaceImgs", spaceImgs);
        
        return "admin/space/adminSpaceDetail";
    }
    
    // 공간 등록 폼
    @GetMapping("/insertForm")
    public String insertForm(HttpSession session, Model model) {
    	String adminId = (String) session.getAttribute("loggedInAdmin");
    	if (adminId == null) {
            return "redirect:/admin"; // 로그인 페이지로 리다이렉트
        }
    	
    	Admin loggedInAdmin = adminMypageService.getAdminById(adminId);
    	if (loggedInAdmin != null) {
            model.addAttribute("admin", loggedInAdmin);
	    	model.addAttribute("space", new Space());
	        model.addAttribute("spaceDetail", new SpaceDetail());
	        model.addAttribute("spaceImg", new SpaceImg());
	        
	        return "admin/space/adminInsertForm";
	    } else {
            return "redirect:/admin"; // 로그인 페이지로 리다이렉트
        }
    }

    @PostMapping("/spaceInsert")
    public String spaceSave(Space space, SpaceDetail spaceDetail, SpaceImg spaceImg) {
        // admNo가 null이 아닌지 확인
        if (space.getAdmNo() == null) {
            // 오류 처리 로직 추가 (예: 예외를 던지거나 사용자에게 오류 메시지 반환)
            throw new IllegalArgumentException("admNo must not be null");
        }

        // 공간 정보 저장
        spaceService.spaceSave(space);

        // 공간 상세 정보 저장
        spaceDetail.setSpace(space);
        spaceDetailService.spaceDetailSave(spaceDetail);

        if(!spaceImg.getFile().isEmpty()) {
			String uploadFileName = fileUtil.saveFile(spaceImg.getFile(), "spaceImg");
			spaceImg.setSpImg(uploadFileName);
			spaceImg.setSpaceDetail(spaceDetail); // 공간 상세 정보와 연결
	        spaceImgService.spaceImgSave(spaceImg);
		}

        return "redirect:/admin/space/spaceList";
    }

    
    // 공간 수정 폼
    @GetMapping("/updateForm/{spNo}/{apiNo}")
    public String updateForm(
            @PathVariable Long spNo, 
            @RequestParam(required = false) Long spDetail, 
            @PathVariable Long apiNo, Model model) {
    	
    	  Space space = spaceService.getSpaceById(spNo);
          SpaceDetail spaceDetail = spaceDetailService.getSpaceDetailBySpaceId(spNo);
          SpaceImg spaceImg = spaceImgService.getSpaceImgsBySpaceId(spNo);
          
        model.addAttribute("updateSpace", space);
        model.addAttribute("updateSpaceDetail", spaceDetail);
        model.addAttribute("updateSpaceImg", spaceImg);
        
        return "admin/space/adminUpdateForm";
    }


    @PostMapping("/spaceUpdate1")
    public String spaceUpdate(Space space, SpaceDetail spaceDetail, SpaceImg spaceImg) {
    	System.out.println("-----------------------------");
    	System.out.println(spaceImg.toString());
        // 공간 정보 업데이트
        Space spaceUpdateData = spaceService.getSpace(space.getSpNo());
        spaceUpdateData.setSpName(space.getSpName());
        spaceUpdateData.setSpHourPrice(space.getSpHourPrice());
        spaceUpdateData.setSpKeyword(space.getSpKeyword());
        spaceService.spaceUpdate(spaceUpdateData);

    	// 공간 상세 정보 저장
        spaceDetail.setSpace(space);
        spaceDetailService.spaceDetailUpdate(spaceDetail);

            SpaceImg existingImage = spaceImgService.getSpaceImg(spaceImg.getApiNo());
        
    	    if(spaceImg.getFile().getSize() > 0) {			// 새로운 업로드 파일이 존재하면
	   			if(existingImage.getSpImg() != null) {	// 기존 파일이 존재하면
	   				fileUtil.deleteFile(existingImage.getSpImg(), "spaceImg");
	   			}
	   			
	   			String uploadFileName = fileUtil.saveFile(spaceImg.getFile(), "spaceImg");
	   			spaceImg.setSpImg(uploadFileName);
	   		 
	    	   spaceImg.setSpaceDetail(spaceDetail); // 공간 상세 정보와 연결
	           spaceImgService.spaceImgUpdate(spaceImg);
    	   }

        return "redirect:/admin/space/" + space.getSpNo();
    }


    // 공간 삭제 처리
    @PostMapping("/spaceDelete/{spNo}")
    public String spaceDelete(@PathVariable Long spNo) {
        SpaceDetail deleteSpaceDetailData = spaceDetailService.getSpaceDetailBySpNo(spNo);
        SpaceImg deleteSpaceImgData = spaceImgService.getSpaceImgsBySpaceId(spNo);
        
        spaceService.spaceDelete(spNo);
        
        if(deleteSpaceImgData.getSpImg()!=null) {
			fileUtil.deleteFile(deleteSpaceImgData.getSpImg(), "spaceImg");
		}
        return "redirect:/admin/space/spaceList";
    }
	
    /* 업로드 파일 보여주기 */
	@ResponseBody
	@GetMapping("/view/{spImg}")
	public ResponseEntity<Resource> viewFileGET(@PathVariable String spImg) {
		return fileUtil.getFile(spImg, "spaceImg");
	}
}
