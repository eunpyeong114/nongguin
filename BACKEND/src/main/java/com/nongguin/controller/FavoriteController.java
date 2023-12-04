package com.nongguin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nongguin.model.dto.Court;
import com.nongguin.model.dto.Favorite;
import com.nongguin.model.service.FavoriteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/favorite")
@Api("찜 컨트롤")
public class FavoriteController {

	@Autowired
	private FavoriteService favoriteService;

	// 찜 등록하기
	@PostMapping("/regist")
	@ApiOperation("찜 등록하기")
	public ResponseEntity<String> registFavorite(@RequestBody Favorite favorite) {
		Boolean result = favoriteService.registFavorite(favorite);
		if (!result)
			return new ResponseEntity<String>("fail to regist", HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<String>("success to regist", HttpStatus.OK);
	}

	// 찜 취소하기
	@DeleteMapping("/cancel")
	@ApiOperation("찜 취소하기")
	public ResponseEntity<String> cancelFavorite(@RequestBody Favorite favorite) {
		Boolean result = favoriteService.cancelFavorite(favorite);
		if (!result)
			return new ResponseEntity<String>("fail to cancel", HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<String>("success to cancel", HttpStatus.OK);
	}

	// 찜한 목록들 조회하기
	@GetMapping("/search/{userId}")
	@ApiOperation("찜한 목록들 조회하기")
	public ResponseEntity<?> getFavorites(@PathVariable int userId) {
		List<Court> result = favoriteService.getFavoritesByUserId(userId);
		if(result==null ||result.size()==0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Court>>(result,HttpStatus.OK);
	}
}
