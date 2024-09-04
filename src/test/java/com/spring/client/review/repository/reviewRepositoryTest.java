package com.spring.client.review.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.admin.space.domain.Space;
import com.spring.client.domain.ClientAuthRepositoryTests;
import com.spring.client.domain.Member;
import com.spring.client.reservation.domain.Reservation;
import com.spring.client.review.domain.Review;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class reviewRepositoryTest {
	
	@Setter(onMethod_=@Autowired)
	private ReviewRepository reviewRepository;
	
	

	/*@Test
	public void reviewInsertTest() {
	    // 첫 번째 리뷰
	    Space space1 = new Space();
	    space1.setSpNo(3L);

	    Member member1 = new Member();
	    member1.setMemberNo(3L);

	    Reservation reservation1 = new Reservation();
	    reservation1.setRNo(4L);

	    log.info("Reservation: " + reservation1);
	    log.info("Reservation ID: " + reservation1.getRNo());

	    Review review1 = new Review();
	    review1.setSpace(space1);
	    review1.setMember(member1);
	    review1.setReservation(reservation1);
	    review1.setRevScore(4);
	    review1.setRevContent("8명이 사용했는데 생각보다 공간이 작지는 않아서 좋았어요. 다만 이전 타임분들이 늦게 자리를 비워서 바로 사용을 못한게 아쉽네요.");
	    review1.setRevDate(LocalDateTime.now());
	    reviewRepository.save(review1);

	    // 두 번째 리뷰
	    Space space2 = new Space();
	    space2.setSpNo(4L);

	    Member member2 = new Member();
	    member2.setMemberNo(4L);

	    Reservation reservation2 = new Reservation();
	    reservation2.setRNo(6L);

	    log.info("Reservation: " + reservation2);
	    log.info("Reservation ID: " + reservation2.getRNo());

	    Review review2 = new Review();
	    review2.setSpace(space2);
	    review2.setMember(member2);
	    review2.setReservation(reservation2);
	    review2.setRevScore(5);
	    review2.setRevContent("시설이 잘 갖춰져 있어서 매우 만족스러웠습니다.");
	    review2.setRevDate(LocalDateTime.now());
	    reviewRepository.save(review2);

	    // 세 번째 리뷰
	    Space space3 = new Space();
	    space3.setSpNo(5L);

	    Review review3 = new Review();
	    review3.setSpace(space3);
	    review3.setMember(member1);
	    review3.setReservation(reservation1);
	    review3.setRevScore(3);
	    review3.setRevContent("조금 좁았지만, 사용하기에는 괜찮았습니다.");
	    review3.setRevDate(LocalDateTime.now());
	    reviewRepository.save(review3);

	    // 네 번째 리뷰
	    Review review4 = new Review();
	    review4.setSpace(space1);
	    review4.setMember(member2);
	    review4.setReservation(reservation2);
	    review4.setRevScore(4);
	    review4.setRevContent("위치가 좋아서 접근성이 뛰어났습니다.");
	    review4.setRevDate(LocalDateTime.now());
	    reviewRepository.save(review4);

	    // 다섯 번째 리뷰
	    Review review5 = new Review();
	    review5.setSpace(space2);
	    review5.setMember(member1);
	    review5.setReservation(reservation1);
	    review5.setRevScore(5);
	    review5.setRevContent("청결하고 모든 시설이 잘 갖춰져 있어서 매우 만족스러운 경험이었습니다.");
	    review5.setRevDate(LocalDateTime.now());
	    reviewRepository.save(review5);

	    // 여섯 번째 리뷰
	    Review review6 = new Review();
	    review6.setSpace(space3);
	    review6.setMember(member2);
	    review6.setReservation(reservation2);
	    review6.setRevScore(4);
	    review6.setRevContent("조용하고 쾌적한 환경이었습니다.");
	    review6.setRevDate(LocalDateTime.now());
	    reviewRepository.save(review6);

	    // 일곱 번째 리뷰
	    Review review7 = new Review();
	    review7.setSpace(space1);
	    review7.setMember(member1);
	    review7.setReservation(reservation1);
	    review7.setRevScore(3);
	    review7.setRevContent("조명이 조금 어두워서 아쉬웠습니다.");
	    review7.setRevDate(LocalDateTime.now());
	    reviewRepository.save(review7);

	    // 여덟 번째 리뷰
	    Review review8 = new Review();
	    review8.setSpace(space2);
	    review8.setMember(member2);
	    review8.setReservation(reservation2);
	    review8.setRevScore(5);
	    review8.setRevContent("시설이 잘 갖춰져 있어서 매우 만족스러웠습니다.");
	    review8.setRevDate(LocalDateTime.now());
	    reviewRepository.save(review8);

	    // 아홉 번째 리뷰
	    Review review9 = new Review();
	    review9.setSpace(space3);
	    review9.setMember(member1);
	    review9.setReservation(reservation1);
	    review9.setRevScore(4);
	    review9.setRevContent("공간이 넓고 깨끗해서 좋았습니다.");
	    review9.setRevDate(LocalDateTime.now());
	    reviewRepository.save(review9);
	}*/
	
	/*@Test
	public void reviewDeleteTest() {
		reviewRepository.deleteById(9L);
	}*/
	
	@Test
	public void SpNoSelectTest() {
		List<Review> reviewList = reviewRepository.findBySpNo(3L);
		for(Review review : reviewList) {
			log.info(review.toString());
		}
	}
	
	@Test
	public void reviewListTest() {
		List<Review> reivewList = (List<Review>) reviewRepository.findBySpNo(3L);
		for(Review review : reivewList) {
			
			log.info(review.toString());
		}
	}





	
	

}
