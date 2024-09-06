package com.spring.admin.space.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.admin.space.domain.Space;
import com.spring.admin.space.repository.SpaceRepository;

import jakarta.persistence.EntityNotFoundException;
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
		//안에 3줄을 리턴 한 줄로 줄일 수 있음
		//return (List<Space>) spaceRepository.findAll();
	}
	
	@Override
	public void spaceHitUpdate(Space space) {
		Optional<Space> spaceOptional = spaceRepository.findById(space.getSpNo());
		Space dataSpace = spaceOptional.get();
		dataSpace.setSpHit(dataSpace.getSpHit()+1);
		spaceRepository.save(dataSpace);
	}
	
	@Override
	public Space spaceContent(Space space) {
//		spaceHitUpdate(space);
		Optional<Space> spaceOptional = spaceRepository.findById(space.getSpNo());
		Space content = spaceOptional.get();
		return content;
	}

	@Override
	public Space getSpace(Long spNo) {
		Optional<Space> spaceOptional = spaceRepository.findById(spNo);
		Space updateData = spaceOptional.get();
		//Space updateData = spaceOptional.findByID(spNo).orElseThrow(()-> new IllegalArgumentException("해당 공간이 없습니다. ID: " + spNO));
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
	public void spaceSave(Space space) {
		spaceRepository.save(space);
	}

	@Override
	public Space getSpaceById(Long spNo) {
		return spaceRepository.findById(spNo)
	            .orElseThrow(() -> new EntityNotFoundException("Space not found with id " + spNo));
	    }

}
