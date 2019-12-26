package com.sp.ex.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.ex.dto.GoogleAccountDTO;
import com.sp.ex.mapper.GoogleMapper;
import com.sp.ex.service.*;

@Service
public class GoogleOAuthServiceImpl implements GoogleOAuthService {

	@Autowired
	GoogleMapper googleMapper;
	@Override
	public String getClientID() {

		return "741951758945-qtt2bbvcf6fhqm6acpim429maj11hqjp.apps.googleusercontent.com";
	}

	@Override
	public String getClientSecret() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCodeURL() {
		String path = "https://accounts.google.com/o/oauth2/v2/auth?"
				+ "scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive.metadata.readonly&"
				+ "redirect_uri=http://localhost:805/ex/Gogl/getCodePage&" + "response_type=code&" + "client_id="
				+ getClientID();
		return path;
	}

	@Override
	public String getToken(HttpServletRequest request) throws Exception {
		URL url = new URL("https://www.googleapis.com/oauth2/v4/token");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		String code = request.getParameter("code");
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		/*
		 * con.addRequestProperty("code",code); con.addRequestProperty("client_id",
		 * "741951758945-qtt2bbvcf6fhqm6acpim429maj11hqjp.apps.googleusercontent.com");
		 * con.addRequestProperty("client_secret","E6alvBW5f0G9YBSuBo_oTWbk");
		 * con.addRequestProperty("redirect_uri",
		 * "http://localhost:805/ex/Gogl/getCodePage");
		 * con.addRequestProperty("grant_type","authorization_code");
		 */
		String param="code=" +code+
				"&client_id=741951758945-qtt2bbvcf6fhqm6acpim429maj11hqjp.apps.googleusercontent.com&" + 
				"client_secret=E6alvBW5f0G9YBSuBo_oTWbk&" + 
				"redirect_uri=http://localhost:805/ex/Gogl/getCodePage&"+ 
				"grant_type=authorization_code";
		System.out.println("param = "+param);
	
		byte[] outputByte=param.getBytes("UTF-8");
		OutputStream os=con.getOutputStream();
		os.write(outputByte);
		os.close();
		int resCode = con.getResponseCode();
		Map<String,List<String>>header= con.getHeaderFields();
		
		System.out.println("header = ");
		for(Map.Entry<String,List<String>>m:header.entrySet()) {
			System.out.println(m.getKey()+" "+m.getValue());
		}
		
		if (con.getErrorStream() != null) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
		}
		System.out.println("rescode = " + resCode);
		return null;
	}

	@Override
	public List<String> getServices() {
		List<String> services=null;
		
		return null;
	}

	@Override
	public String getAccountID(String userID) {
		System.out.println("user id = "+userID);

		return googleMapper.getAccountInfo(userID).getAccount_ID();
	}

}
