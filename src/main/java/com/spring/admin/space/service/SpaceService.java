package com.spring.admin.space.service;

import java.util.List;

import com.spring.admin.space.domain.Space;

public interface SpaceService {
	public List<Space> spaceList(Space space);

	public Space spaceContent(Space space);

	public Space getSpace(Long spNo);

	public void spaceUpdate(Space space);

	public void spaceDelete(Space space);

	void spaceHitUpdate(Space space);

	public void spaceSave(Space space);

	public Space getSpaceById(Long spNo);

}
