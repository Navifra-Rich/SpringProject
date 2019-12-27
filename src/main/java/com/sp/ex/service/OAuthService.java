package com.sp.ex.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface OAuthService {
	public String getAccountID(String userID);	//연결되어있는 구글 계정을 가져옴
	public String getClientID();				//oauth의 clinet id 반환
	public String getClientSecret();			//oauth의 clinet secret 반환
	public String getCodeURL();					//code 반환
	public String getToken(HttpServletRequest request)throws Exception;	//token 반환
	public List<String> getServices();			//제공하고있는 서비스 권한 목록을 반환
	String getAccessToken(String userID);
}
