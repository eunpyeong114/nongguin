package com.nongguin.model.service;

import com.nongguin.model.dto.Review;

public interface ReviewService {

	public boolean registReview(Review review);

	public Boolean deleteReview(Review review);

}
