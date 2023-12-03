package com.nongguin.model.dto;

import java.util.Date;

public class Review {
	int courtId;
	int paymentId;
	String reviewContent;
	Date reviewCreatedAt;

	public Review() {

	}

	public Review(int courtId, int paymentId, String reviewContent, Date reviewCreatedAt) {
		super();
		this.courtId = courtId;
		this.paymentId = paymentId;
		this.reviewContent = reviewContent;
		this.reviewCreatedAt = reviewCreatedAt;
	}

	public int getCourtId() {
		return courtId;
	}

	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Date getReviewCreatedAt() {
		return reviewCreatedAt;
	}

	public void setReviewCreatedAt(Date reviewCreatedAt) {
		this.reviewCreatedAt = reviewCreatedAt;
	}

	@Override
	public String toString() {
		return "Review [courtId=" + courtId + ", paymentId=" + paymentId + ", reviewContent=" + reviewContent
				+ ", reviewCreatedAt=" + reviewCreatedAt + "]";
	}

}
