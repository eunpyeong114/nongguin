package com.nongguin.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nongguin.model.dao.MatchDao;
import com.nongguin.model.dao.Match_UserDao;
import com.nongguin.model.dao.PaymentDao;
import com.nongguin.model.dto.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentDao paymentDao;

	@Autowired
	Match_UserDao muDao;

	@Autowired
	MatchDao matchDao;

//	@Value("imp_key")
//	String imp_key;
//	
//	@Value("imp_secret")
//	String imp_secret;
	String imp_key = "4470584774435334";
	String imp_secret = "RwFrwEngoSjvt9tekRZwVpjui1vSe6OfsTEwisiP11kfz3H9QAfRtE6usgiQMkZPpBF5JOUDCQy9X917";

	@Transactional
	@Override
	public boolean payForMatch(Payment payment, int userId, int matchId) {
		// payment 결제 데이터 저장
		try {
			int result = paymentDao.payForMatch(payment);
			if (result <= 0)
				return false;
		} catch (Throwable err) {
			System.out.println(err);
		}
		// 매치 데이터 저장
		try {
			int mResult = matchDao.plusApplicantCnt(matchId);
			if (mResult <= 0)
				return false;
		} catch (Throwable err) {
			System.out.println(err);
		}
		// 매치_유저 중간테이블 저장
		try {
			int muResult = muDao.insertData(userId, matchId);
			if (muResult <= 0)
				return false;
		} catch (Throwable err) {
			System.out.println(err);
		}
		return true;
	}

	// 아임포트에서 토큰 발급받아오기
	@Override
	public String getToken() throws IOException {
		HttpsURLConnection conn = null;

		URL url = new URL("https://api.iamport.kr/users/getToken");

		conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "application/json");

		conn.setDoOutput(true);
		JSONObject json = new JSONObject();

		json.put("imp_key", imp_key);
		json.put("imp_secret", imp_secret);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

		bw.write(json.toString());
		bw.flush();
		bw.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		String token = br.readLine();
		JSONObject jsonObject = new JSONObject(token);
		JSONObject response = jsonObject.getJSONObject("response");
		String accessToken = response.getString("access_token");
		conn.disconnect();
		return accessToken;
	}

	// 금액확인 후 저장하기
	@Transactional
	public boolean checkPaid(Payment payment) throws Exception {
		String token = getToken();
		HttpsURLConnection conn = null;
		String impUid = payment.getPaymentImpUid();
		URL url = new URL("https://api.iamport.kr/payments/" + impUid);
		conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Authorization", token);
		conn.setDoOutput(true);
		JSONObject json = new JSONObject();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

		bw.write(json.toString());
		bw.flush();
		bw.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		String result = br.readLine();
		JSONObject jsonObject = new JSONObject(result);
		JSONObject response = jsonObject.getJSONObject("response");
		int amount = response.getInt("amount");
		System.out.println(amount);

		// 포트원 결제금액과 비교 검증
		if (amount != payment.getPaymentAmount())
			throw new Exception("실제 결제된 금액이 일치하지 않습니다");
		// 이전에 해당 매치를 결제한 이력이 있다면
		Payment check = paymentDao.searchPrePayment(payment);
		if (check != null && check.getPaymentStatus().equals("결제완료"))
			return false;
		// 금액이 동일하다면 db 저장
		// payment 결제 데이터 저장
		try {
			System.out.println(payment);
			int pResult = paymentDao.payForMatch(payment);
			System.out.println("pay저장");
			if (pResult <= 0)
				return false;
		} catch (Throwable err) {
			System.out.println(err);
		}
		// 매치 데이터 저장
		try {
			int mResult = matchDao.plusApplicantCnt(payment.getMatchId());
			System.out.println("match저장");
			if (mResult <= 0)
				return false;
		} catch (Throwable err) {
			System.out.println(err);
		}
		// 매치_유저 중간테이블 저장
		try {
			int muResult = muDao.insertData(payment.getUserId(), payment.getMatchId());
			System.out.println("matchuser저장");
			if (muResult <= 0)
				return false;
		} catch (Throwable err) {
			System.out.println(err);
		}
		return true;
	}

	// 결제 취소
	@Transactional
	@Override
	public boolean checkCancel(Payment payment) throws IOException {
		// 해당 결제 금액 및 impuid 가져오기
		Payment paidInfo = paymentDao.getPaymentByMatchIdAndUserId(payment);
		if (paidInfo == null || paidInfo.getPaymentStatus().equals("결제취소"))
			return false;
		System.out.println("payment:" + payment);
		System.out.println("painInfo:" + paidInfo);
		int paymentAmount = paidInfo.getPaymentAmount();
		String impUid = paidInfo.getPaymentImpUid();
		// 토큰 발급받기
		String token = getToken();
		HttpsURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/payments/cancel");
		conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Authorization", token);
		conn.setDoOutput(true);

		String requestBody = "{\"imp_uid\":\"" + impUid + "\"}";
		try (OutputStream os = conn.getOutputStream()) {
			byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}

		JSONObject json = new JSONObject();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

		bw.write(json.toString());
		bw.flush();
		bw.close();

		int responseCode = conn.getResponseCode();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		String result = br.readLine();

		JSONObject jsonObject = new JSONObject(result);
		JSONObject response = jsonObject.getJSONObject("response");
		// 포트원에서 받은 금액
		int amount = response.getInt("amount");
		if (amount != paymentAmount) {
			throw new IOException();
		}
		paidInfo.setPaymentAmount(-amount);
		// 결제 db에 결제취소 등록
		int pResult = paymentDao.cancel(paidInfo);
		if (pResult <= 0) {
			throw new IOException();
		}
		// match_user 테이블 삭제
		int mucResult = muDao.deleteMatch(paidInfo.getMatchId(), payment.getUserId());
		if (mucResult <= 0) {
			throw new IOException();
		}
		// match 테이블 applicantCnt--
		int mResult = matchDao.minusCnt(paidInfo.getMatchId());
		if (mResult <= 0) {
			throw new IOException();
		}
		conn.disconnect();
		return true;
	}

	@Override
	public Payment getPaymentId(int userId, int matchId) {
		return paymentDao.getPaymentId(userId, matchId);
	}

}
