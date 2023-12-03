package com.nongguin.model.dto;

import java.sql.Timestamp;
import java.util.Date;

public class User {
	private int userId;
	private String userName;
	private String userEmail;
	private String userPass;
	private String userGender;
	private String userAddress;
	private String userPhone;
	private boolean userIsDeleted;
	private Timestamp userCreatedAt;

	public User() {

	}

	public User(int userId, String userName, String userEmail, String userPass, String userGender, String userAddress,
			String userPhone, boolean userIsDeleted, Timestamp userCreatedAt) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPass = userPass;
		this.userGender = userGender;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userIsDeleted = userIsDeleted;
		this.userCreatedAt = userCreatedAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public boolean isUserIsDeleted() {
		return userIsDeleted;
	}

	public void setUserIsDeleted(boolean userIsDeleted) {
		this.userIsDeleted = userIsDeleted;
	}

	public Date getUserCreatedAt() {
		return userCreatedAt;
	}

	public void setUserCreatedAt(Timestamp userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPass="
				+ userPass + ", userGender=" + userGender + ", userAddress=" + userAddress + ", userPhone=" + userPhone
				+ ", userIsDeleted=" + userIsDeleted + ", userCreatedAt=" + userCreatedAt + "]";
	}

}
