package com.nongguin.model.dto;

import java.util.Date;

public class Payment {

	private int paymentId;
	private int paymentAmount;
	private String paymentStatus;
	private Date paymentDate;
	private String paymentImpUid;
	private int userId;
	private int matchId;

	public Payment() {

	}

	public Payment(int paymentId, int paymentAmount, String paymentStatus, Date paymentDate, String paymentImpUid,
			int userId, int matchId) {
		super();
		this.paymentId = paymentId;
		this.paymentAmount = paymentAmount;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.paymentImpUid = paymentImpUid;
		this.userId = userId;
		this.matchId = matchId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentImpUid() {
		return paymentImpUid;
	}

	public void setPaymentImpUid(String paymentImpUid) {
		this.paymentImpUid = paymentImpUid;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentAmount=" + paymentAmount + ", paymentStatus="
				+ paymentStatus + ", paymentDate=" + paymentDate + ", paymentImpUid=" + paymentImpUid + ", userId="
				+ userId + ", matchId=" + matchId + "]";
	}


}
