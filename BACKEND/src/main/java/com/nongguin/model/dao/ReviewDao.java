package com.nongguin.model.dao;

import java.util.List;

import com.nongguin.model.dto.Review;

public interface ReviewDao {

	public int registReview(Review review);

	public int deleteReview(Review review);

	public int modifyReview(Review review);

	public List<Review> getReviewsByCourtId(int courtId);

	public Review getReviewByPaymentId(int paymentId);

}
