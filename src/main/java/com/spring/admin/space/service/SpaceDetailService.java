package com.spring.admin.space.service;

import java.util.List;

import com.spring.admin.space.domain.SpaceDetail;

public interface SpaceDetailService {
	List<SpaceDetail> spaceDetailList(SpaceDetail spaceDetail);
	void spaceDetailInsert(SpaceDetail spaceDetail);
	SpaceDetail spaceDetailExplanation (SpaceDetail spaceDetail);
	SpaceDetail spaceDetailUpdateForm (SpaceDetail spaceDetail);
	void spaceDetailUpdate (SpaceDetail spaceDetail);
	void spaceDetailDelete (SpaceDetail spaceDetail);
}
