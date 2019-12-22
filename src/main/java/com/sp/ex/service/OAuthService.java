package com.sp.ex.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface OAuthService {
	public String getClientID();
	public String getClientSecret();
	public String getCodeURL();
	public String getToken(HttpServletRequest request)throws Exception;
	
}
