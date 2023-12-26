package com.nongguin.model.dao;

import com.nongguin.model.dto.Payment;

public interface PaymentDao {

	int payForMatch(Payment payment);

	Payment getPaymentByMatchIdAndUserId(Payment payment);
	
	// 한사람이 같은 매치에 또 결제하는지 체크 
	Payment searchPrePayment(Payment payment);

	int cancel(Payment payment);
	
	// paymentId 조회
	Payment getPaymentId(int userId, int matchId);

}
