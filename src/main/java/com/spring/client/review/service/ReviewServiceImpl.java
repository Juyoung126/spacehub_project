package com.spring.client.review.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.client.review.domain.Review;
import com.spring.client.review.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	

	private final ReviewRepository reviewRepository;
	
	 @Override
	    public List<Review> findBySpNo(Long spNo) {
	        return reviewRepository.findBySpNo(spNo);
	    }
	@Override
	public List<Review> reviewList(Review review) {
		
		List<Review> reviewList = (List<Review>)reviewRepository.findBySpNo(review.getSpace().getSpNo());
		
		return reviewList;
	}

	@Override
	public Review reviewInsert(Review review) {
		
		Review result = reviewRepository.save(review);
		return result;
	}
	



	@Override
	public Review reviewUpdate(Review review) {
		Optional<Review> reviewOptional = reviewRepository.findById(review.getRevNo());
		Review updateReview = reviewOptional.get();	
		
		updateReview.setRevScore(review.getRevScore());
		updateReview.setRevContent(review.getRevContent());
		Review result = reviewRepository.save(updateReview);
		
		
		return result;
		
	}

	@Override
	public void reviewDelete(Review review) {
		reviewRepository.deleteById(review.getRevNo());
		
	}

	
	

}
