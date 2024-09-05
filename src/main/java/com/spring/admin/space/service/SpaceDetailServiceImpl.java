package com.spring.admin.space.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.admin.space.domain.SpaceDetail;
import com.spring.admin.space.repository.SpaceDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpaceDetailServiceImpl implements SpaceDetailService {

	@Autowired
	public final SpaceDetailRepository spaceDetailRepository;
	
	@Override
	public List<SpaceDetail> spaceDetailList(SpaceDetail spaceDetail) {
		List<SpaceDetail> spaceDetailList = spaceDetailRepository.findAll();
		return spaceDetailList;
	}

	@Override
	public void spaceDetailInsert(SpaceDetail spaceDetail) {
		spaceDetailRepository.save(spaceDetail);	
	}
	
	@Override	// 상세페이지
	public SpaceDetail spaceDetailExplanation(SpaceDetail spaceDetail) {
		Optional<SpaceDetail> spaceDetailOptional = spaceDetailRepository.findById(spaceDetail.getSpDetail());
		SpaceDetail description = spaceDetailOptional.get();
		return description;
	}

	@Override
	public SpaceDetail spaceDetailUpdateForm(SpaceDetail spaceDetail) {
		Optional<SpaceDetail> spaceDetailOptional = spaceDetailRepository.findById(spaceDetail.getSpDetail());
		SpaceDetail updateData = spaceDetailOptional.get();
		return updateData;
	}

	@Override
	public void spaceDetailUpdate(SpaceDetail spaceDetail) {
		Optional<SpaceDetail> spaceDetailOptional = spaceDetailRepository.findById(spaceDetail.getSpDetail());
		SpaceDetail updateSpaceDetail = spaceDetailOptional.get();
		updateSpaceDetail.setSpDescription(spaceDetail.getSpDescription());
		updateSpaceDetail.setSpEquipment(spaceDetail.getSpEquipment());
		updateSpaceDetail.setSpStartTime(spaceDetail.getSpStartTime());
		updateSpaceDetail.setSpEndTime(spaceDetail.getSpEndTime());
		
		spaceDetailRepository.save(updateSpaceDetail);
		
	}

	@Override
	public void spaceDetailDelete(SpaceDetail spaceDetail) {
		spaceDetailRepository.deleteById(spaceDetail.getSpDetail());	
	}

}


