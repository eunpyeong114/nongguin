package com.nongguin.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.nongguin.model.dto.User;
import com.nongguin.model.service.UserService;
import com.nongguin.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(tags = "유저 컨트롤러")
public class UserController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	// 회원가입
	@PostMapping("/signup")
	@ApiOperation("회원가입")
	public ResponseEntity<String> signUp(@RequestBody User user) {
		int result = userService.signUp(user);
		if (result==1)
			return new ResponseEntity<String>("회원가입 성공", HttpStatus.ACCEPTED);
		else if(result==-1)
			return new ResponseEntity<String>("이미 존재하는 이메일입니다", HttpStatus.NOT_ACCEPTABLE);
		else 
			return new ResponseEntity<String>("서버 에러",HttpStatus.NOT_ACCEPTABLE);
	}

	// 로그인
	@PostMapping("/signin")
	@ApiOperation("로그인")
	public ResponseEntity<Map<String, Object>> signIn(@RequestBody User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpStatus status = null;
		User dbUser = userService.signIn(user);
		try {
			if ((dbUser.getUserEmail() != null && !dbUser.isUserIsDeleted())
					|| (dbUser.getUserEmail().length() > 0 && !dbUser.isUserIsDeleted())) {
				if (dbUser.getUserPass().equals(user.getUserPass())) {
					result.put("access-token",
							jwtUtil.createToken("userEmail", dbUser.getUserEmail(), "userId", dbUser.getUserId()));
					result.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				}
			} else {
				result.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (UnsupportedEncodingException e) {
			result.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	// 로그아웃
	@GetMapping("logout")
	@ApiOperation("로그아웃")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 회원탈퇴
	@PutMapping("/signout/{userId}")
	@ApiOperation("회원탈퇴")
	public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") int userId) {
		boolean result = userService.deleteUser(userId);
		if (!result)
			return new ResponseEntity<String>("false", HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<String>("true", HttpStatus.ACCEPTED);
	}

	// 회원정보조회
	@GetMapping("/id/{userId}")
	@ApiOperation("회원정보조회")
	public ResponseEntity<?> getUserByUserId(@PathVariable(value = "userId") int userId) {
		System.out.println(userId);
		User user = userService.getUserByUserId(userId);
		if (user == null || user.isUserIsDeleted())
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 회원정보수정
	@PutMapping("/{userId}")
	@ApiOperation("비밀번호, 주소, 전화번호만 수정 가능하다")
	public ResponseEntity<String> modifyUser(@RequestBody User user) {
		boolean result = userService.modifyUser(user);
		if (!result)
			return new ResponseEntity<String>("false", HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<String>("true", HttpStatus.OK);
	}

	// 이메일로 유저 조회
	@GetMapping("/email/{userEmail}")
	@ApiOperation("이메일로 유저정보 조회")
	public ResponseEntity<?> getUserByUserEmail(@PathVariable(value = "userEmail") String userEmail) {
		User user = userService.getUserByEmail(userEmail);
		if (user == null || user.isUserIsDeleted())
			return new ResponseEntity<String>("no user", HttpStatus.NO_CONTENT);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	// 비밀번호 재등록
}
