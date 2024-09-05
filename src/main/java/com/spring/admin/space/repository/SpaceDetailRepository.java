package com.spring.admin.space.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.admin.space.domain.SpaceDetail;

@Repository
public interface SpaceDetailRepository extends JpaRepository<SpaceDetail, Long> {

}
