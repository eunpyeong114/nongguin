package com.nongguin.model.dao;

import com.nongguin.model.dto.Review;

public interface ReviewDao {

	public int registReview(Review review);

	public int deleteReview(Review review);

}
