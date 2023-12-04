package com.nongguin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongguin.model.dao.ReviewDao;
import com.nongguin.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDao reviewDao;

	@Override
	public boolean registReview(Review review) {
		int result = reviewDao.registReview(review);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public Boolean deleteReview(Review review) {
		int result = reviewDao.deleteReview(review);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public Boolean modifyReview(Review review) {
		int result = reviewDao.modifyReview(review);
		if(result>0)
			return true;
		return false;
	}

	@Override
	public List<Review> getReviewsByCourtId(int courtId) {
		return reviewDao.getReviewsByCourtId(courtId);
	}

	@Override
	public Review getReviewByPaymentId(int paymentId) {
		return reviewDao.getReviewByPaymentId(paymentId);
	}

}
