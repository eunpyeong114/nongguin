package com.nongguin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nongguin.model.dto.Review;
import com.nongguin.model.service.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/review")
@Api("리뷰 컨트롤러")
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	// 리뷰 등록하기
	@PostMapping("/regist")
	@ApiOperation("리뷰 등록하기")
	public ResponseEntity<?> registReview(@RequestBody Review review) {
		Boolean result = reviewService.registReview(review);
		if (!result)
			return new ResponseEntity<String>("fail to regist", HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<String>("success to regist", HttpStatus.OK);
	}

	// 리뷰 삭제하기
	@DeleteMapping("/delete")
	@ApiOperation("리뷰 삭제하기")
	public ResponseEntity<?> deleteReview(@RequestBody Review review) {
		Boolean result = reviewService.deleteReview(review);
		if (!result)
			return new ResponseEntity<String>("fail to delete", HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<String>("success to delete", HttpStatus.OK);
	}

	// 리뷰 수정하기
	@PutMapping("/modify")
	@ApiOperation("리뷰 수정하기")
	public ResponseEntity<?> modifyReview(@RequestBody Review review) {
		Boolean result = reviewService.modifyReview(review);
		if (!result)
			return new ResponseEntity<String>("fail to modify", HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<String>("success to modify", HttpStatus.OK);
	}

	// 리뷰 전체 조회하기
	@GetMapping("/search")
	@ApiOperation("농구장별 전체 리뷰 조회하기")
	public ResponseEntity<?> getReviewsByCourtId(int courtId) {
		List<Review> reviews = reviewService.getReviewsByCourtId(courtId);
		if (reviews == null || reviews.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
	}

	// 유저 리뷰 조회하기
	@GetMapping("/search/{paymentId}")
	@ApiOperation("매치 결제별 리뷰 조회하기")
	public ResponseEntity<?> getReviewByPaymentId(@PathVariable int paymentId) {
		Review review = reviewService.getReviewByPaymentId(paymentId);
		if (review == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
}
