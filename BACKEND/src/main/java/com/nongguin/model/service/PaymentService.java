package com.nongguin.model.service;

import java.io.IOException;

import com.nongguin.model.dto.Match;
import com.nongguin.model.dto.Payment;

public interface PaymentService {

	boolean payForMatch(Payment payment, int userId, int matchId);

	String getToken() throws IOException;

	boolean checkPaid(Payment payment) throws IOException, Exception;

	boolean checkCancel(Payment payment) throws IOException;

	Payment getPaymentId(int userId, int matchId);
}
