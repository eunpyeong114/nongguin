package com.nongguin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nongguin.model.dto.Payment;
import com.nongguin.model.service.MatchService;
import com.nongguin.model.service.PaymentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/payment")
@Api(tags = "결제 컨트롤러")
public class PaymentController {
	@Autowired
	PaymentService paymentService;

	@Autowired
	MatchService matchService;

	// 매치 결제
	@PostMapping("/payment")
	@ApiOperation("매치 결제")
	public ResponseEntity<?> pay(@RequestBody Payment payment) throws Exception {
		boolean result = paymentService.checkPaid(payment);
		if (!result)
			return new ResponseEntity<String>("fail to pay", HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<String>("success to pay", HttpStatus.OK);
	}

	// 매치 결제 취소
	@PostMapping("/cancel")
	@ApiOperation("매치 결제 취소")
	public ResponseEntity<?> cancelPayment(@RequestBody Payment payment) throws IOException {
		boolean result = paymentService.checkCancel(payment);
		if (!result)
			return new ResponseEntity<String>("fail to cancel", HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<String>("success to cancel", HttpStatus.OK);
	}

	// PaymentId 조회
	@GetMapping("/getid/{matchId}/{userId}")
	@ApiOperation("매치 id 조회")
	public ResponseEntity<?> getPaymentId(@PathVariable int userId,@PathVariable int matchId) {
		Payment result = paymentService.getPaymentId(userId, matchId);
		if (result == null || !result.getPaymentStatus().equals("결제취소"))
			return new ResponseEntity<String>("fail to getId", HttpStatus.NO_CONTENT);
		return new ResponseEntity<Payment>(result, HttpStatus.OK);
	}

}
