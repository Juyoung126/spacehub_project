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
@Table(name = "spacehub_space_img")
@SequenceGenerator(name = "space_img_seq", sequenceName = "space_img_seq", initialValue = 1, allocationSize = 1)
public class SpaceImg {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "space_img_seq")
	@Column(name = "api_no")
	private Long apiNo;
	
	@Id
	@Column(name = "sp_img")
	private String spImg;
	
}
