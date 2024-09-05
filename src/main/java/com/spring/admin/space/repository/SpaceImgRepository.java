package com.spring.admin.space.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.admin.space.domain.SpaceImg;

@Repository
public interface SpaceImgRepository extends JpaRepository<SpaceImg, Long> {

}
