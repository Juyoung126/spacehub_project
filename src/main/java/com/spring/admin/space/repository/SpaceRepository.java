package com.spring.admin.space.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.admin.space.domain.Space;

public interface SpaceRepository extends JpaRepository<Space, Long>{

	// 검색 - 메서드 쿼리 생성 : spName 필드 값과 정확히 일치하는 space 엔티티 검색
	Space findBySpName(String spName);

	// 검색 - 메서드 부분 일치 검색 : spName필드에 특정 문자열 포함된 모든 space 엔티티 검색
	List<Space> findBySpNameContaining(String spName);

	// 모든 게시물 조회 (JPQL) : JPQL(Java Persistence Query Language)을 사용하여 Space 엔티티의 모든 레코드를 가져옴
	// select * from Space 와 유사한 쿼리.
	@Query("SELECT b FROM Space b")
	public List<Space> spaceList();

//	@Query("SELECT b FROM Space b WHERE b.sp_no =?1")
//	public Space spaceDetail(Long spNo);

//	@Modifying
//	@Query("UPDATE Space b SET b.sp_name =?2 WHERE b.sp_no =?1")
//	public int spaceUpdate(Long spNo, String spName);
	
	
	//spNo, admNo, spName, spCapacitym spHourPrice, spKeyword, spMainImage
}
