package com.sp.ex.dto;

public class TokenDTO {
	String user_ID;
	String Access_Token;
	String Refresh_Token;
	String Account_ID;
	
	public TokenDTO(String user_ID, String access_Token, String refresh_Token, String account_ID) {
		this.user_ID = user_ID;
		Access_Token = access_Token;
		Refresh_Token = refresh_Token;
		Account_ID = account_ID;
	}
	public TokenDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}
	public String getAccess_Token() {
		return Access_Token;
	}
	public void setAccess_Token(String access_Token) {
		Access_Token = access_Token;
	}
	public String getRefresh_Token() {
		return Refresh_Token;
	}
	public void setRefresh_Token(String refresh_Token) {
		Refresh_Token = refresh_Token;
	}
	public String getAccount_ID() {
		return Account_ID;
	}
	public void setAccount_ID(String account_ID) {
		Account_ID = account_ID;
	}
}
