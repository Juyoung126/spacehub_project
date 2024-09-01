package com.spring.admin.space.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.admin.space.domain.Space;
import com.spring.admin.space.repository.SpaceRepository;

import lombok.Setter;

@Service
public class SpaceServiceImpl implements SpaceService {

	@Setter(onMethod_ = @Autowired)
	private SpaceRepository spaceRepository;
	
	@Override
	public List<Space> spaceList(Space space) {
		List<Space> spaceList = null;
		spaceList = (List<Space>) spaceRepository.findAll();
		return spaceList;
	}

}
