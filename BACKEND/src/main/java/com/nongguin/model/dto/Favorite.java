package com.nongguin.model.dto;

public class Favorite {
	private int courtId;
	private int userId;

	public Favorite() {

	}

	public Favorite(int courtId, int userId) {
		super();
		this.courtId = courtId;
		this.userId = userId;
	}

	public int getCourtId() {
		return courtId;
	}

	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Favorite [courtId=" + courtId + ", userId=" + userId + "]";
	}

}
