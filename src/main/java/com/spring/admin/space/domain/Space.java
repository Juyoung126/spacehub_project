package com.spring.admin.space.domain;

import org.hibernate.annotations.ColumnDefault;

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
@Table(name = "spacehub_space")
@SequenceGenerator(name = "space_seq", sequenceName = "space_seq", initialValue = 1, allocationSize = 1)
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "space_seq")
    @Column(name = "sp_no")
    private Long spNo;	// 공간 식별번호

    @Column(name = "adm_no", nullable = false)
    private Long admNo;	// 관리자 식별번호

    @Column(name = "sp_name", nullable = false)
    private String spName;	// 공간 이름

    @Column(name = "sp_capacity", length = 30, nullable = false)
    private String spCapacity;	// 이용 가능 인원

    @Column(name = "sp_hour_price", nullable = false)
    private Long spHourPrice;	// 시간당 가격

    @Column(name = "sp_keyword", nullable = false)
    private String spKeyword;	// 이용 목적 키워드

    @Column(name = "sp_main_image")
    private String spMainImage;	// 대표 이미지

    @ColumnDefault(value = "0")
	private int spHit;		// 조회수

}
