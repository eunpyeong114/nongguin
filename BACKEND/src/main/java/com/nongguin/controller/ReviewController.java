package com.nongguin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
//	@PutMapping("/update")
//	@ApiOperation("리뷰 수정하기")
//	public ResponseEntity<T>
	// 리뷰 전체 조회하기

	// 유저 리뷰 조회하기

}
