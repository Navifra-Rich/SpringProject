package com.sp.ex.dto;

public class GoogleAccountDTO {
	private String user_ID;
	private String access_token;
	private String refresh_token;
	private String account_ID;
	public String getUser_ID() {
		return user_ID;
	}
	public void setUserID(String userID) {
		this.user_ID = userID;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getAccount_ID() {
		return account_ID;
	}
	public void setAccount_ID(String account_ID) {
		this.account_ID = account_ID;
	}
}
