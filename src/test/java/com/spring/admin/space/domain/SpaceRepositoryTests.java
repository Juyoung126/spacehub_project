package com.spring.admin.space.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.admin.space.repository.SpaceRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class SpaceRepositoryTests {
	
	@Setter (onMethod_ = @Autowired)
	private SpaceRepository spaceRepository;
	
//	private Space space;
	
	/* 
	@Test
	public void testCreateSpace() {
		Space space = new Space();
		space.setAdmNo(1L);
		space.setSpName("테스트5");
		space.setSpCapacity("20명");
		space.setSpHourPrice(10000L);
		space.setSpKeyword("스터디룸, 미팅룸");
		space.setSpMainImage("/images/client/space/carrie-hays-mdLoaKr9vt8-unsplash.jpg");
		space.setSpHit(0);

		Space savedSpace = spaceRepository.save(space);

		assertNotNull(savedSpace);
		assertThat(savedSpace.getSpNo()).isNotNull();
		log.info("Saved Space: {}", savedSpace);
	}  */

	/*
	@Test
	public void spaceListTest() {
		List<Space> spaceList = spaceRepository.spaceList();
		for(Space space : spaceList) {
			log.info(space.toString());
		}
	}*/
	
	/*
	@Test
	public void testFindBySpName() {
		String spName = "Test Space";
		Space space = spaceRepository.findBySpName(spName);

		assertNotNull(space);
		assertEquals(spName, space.getSpName());
		log.info("Found Space: {}", space);
	}   */
	
	/* 
	@Test
	public void testFindBySpNameContaining() {
		String keyword = "Test";
		List<Space> spaces = spaceRepository.findBySpNameContaining(keyword);

		assertThat(spaces).isNotEmpty();
		spaces.forEach(space -> log.info("Found Space: {}", space));
	}  */
	
	
	/* 
	@Test
	public void testSpaceDetail() {
		Long spNo = 1L; // 테스트 데이터에 따라 변경
		Space space = spaceRepository.spaceDetail(spNo);

		assertNotNull(space);
		assertEquals(spNo, space.getSpNo());
		log.info("Space Detail: {}", space);
	}  */

//	@Test
//	@Transactional
//	public void testSpaceUpdate() {
//		Long spNo = 1L; // 테스트 데이터에 따라 변경
//		String newSpName = "Updated Space Name";
//		int updatedRows = spaceRepository.spaceUpdate(spNo, newSpName);
//
//		assertEquals(1, updatedRows);
//
//		Space updatedSpace = spaceRepository.spaceDetail(spNo);
//		assertEquals(newSpName, updatedSpace.getSpName());
//		log.info("Updated Space: {}", updatedSpace);
//	}

	/*
	@Test
	@Transactional
	public void testSpaceDelete() {
		Long spNo = 1L; // 테스트 데이터에 따라 변경
		int deletedRows = spaceRepository.spaceDelete(spNo);

		assertEquals(1, deletedRows);

		Optional<Space> deletedSpace = spaceRepository.findById(spNo);
		assertThat(deletedSpace).isEmpty();
		log.info("Space deleted successfully");
	}   */

//	@Test
//	@Transactional
//	public void testSpaceHitUpdate() {
//		Long spNo = 1L; // 테스트 데이터에 따라 변경
//		spaceRepository.spaceHitUpdate(spNo);
//
//		Space updatedSpace = spaceRepository.spaceDetail(spNo);
//		assertEquals(1, updatedSpace.getHit());
//		log.info("Space Hit Updated: {}", updatedSpace);
//	}
	
}
