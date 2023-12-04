package com.nongguin.model.service;

import java.util.List;

import com.nongguin.model.dto.Review;

public interface ReviewService {

	public boolean registReview(Review review);

	public Boolean deleteReview(Review review);

	public Boolean modifyReview(Review review);

	public List<Review> getReviewsByCourtId(int courtId);

	public Review getReviewByPaymentId(int paymentId);

}
