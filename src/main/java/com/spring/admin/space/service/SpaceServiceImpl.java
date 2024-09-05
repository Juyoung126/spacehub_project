package com.spring.admin.space.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.admin.space.domain.Space;
import com.spring.admin.space.repository.SpaceRepository;

import lombok.Setter;

@Service
public class SpaceServiceImpl implements SpaceService {

	@Setter(onMethod_ = @Autowired)
	private SpaceRepository spaceRepository;
    
    // Space 관련 메서드
	@Override
	public List<Space> spaceList(Space space) {
		List<Space> spaceList = null;
		spaceList = (List<Space>) spaceRepository.findAll();
		return spaceList;
	}
	
	@Override
	public void spaceHitUpdate(Space space) {
		Optional<Space> spaceOptional = spaceRepository.findById(space.getSpNo());
		Space dataSpace = spaceOptional.get();
		dataSpace.setSpHit(dataSpace.getSpHit()+1);
		spaceRepository.save(dataSpace);
	}
	
	@Override
	public Space spaceDetail(Space space) {
//		spaceHitUpdate(space);
		Optional<Space> spaceOptional = spaceRepository.findById(space.getSpNo());
		Space detail = spaceOptional.get();
		return detail;
	}

	@Override
	public Space getSpace(Long spNo) {
		Optional<Space> spaceOptional = spaceRepository.findById(spNo);
		Space updateData = spaceOptional.orElseThrow();
		return updateData;
	}

	@Override
	public void spaceUpdate(Space space) {
		Optional<Space> spaceOptional = spaceRepository.findById(space.getSpNo());
		Space updateSpace = spaceOptional.get();
		
		updateSpace.setSpName(space.getSpName());
		updateSpace.setSpCapacity(space.getSpCapacity());
		updateSpace.setSpKeyword(space.getSpKeyword());
		updateSpace.setSpHourPrice(space.getSpHourPrice());
		
		spaceRepository.save(updateSpace);
	}

	@Override
	public void spaceDelete(Space space) {
		spaceRepository.deleteById(space.getSpNo());
	}

	@Override
	public void spaceInsert(Space space) {
		spaceRepository.save(space);
	}

}
