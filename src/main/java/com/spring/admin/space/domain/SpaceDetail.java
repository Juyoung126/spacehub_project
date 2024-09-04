package com.spring.admin.space.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "spacehub_space_detail")
@SequenceGenerator(name = "space_detail_seq", sequenceName = "space_detail_seq", initialValue = 1, allocationSize = 1)
public class SpaceDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "space_detail_seq")
    @Column(name = "sp_detail")
	private Long spDetail;	// 상세정보 번호
	
	@Id
	@Column(name = "sp_description")
	private String spDescription;	// 공간 상세 설명
	
	@Id
	@Column(name = "sp_description")
	private String spEquipment;		// 비치된 물품
	
	@Id
	@Column(name = "sp_description")
	private String spRules;			// 이용 수칙
	
	@Id
	@Column(name = "sp_description")
	private String spStartTime;		// 시작 시간
	
	@Id
	@Column(name = "sp_end_time")
	private String spEndTime;		// 이용 시간 
}
