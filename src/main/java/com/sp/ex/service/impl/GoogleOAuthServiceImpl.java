package com.sp.ex.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.ex.dto.TokenDTO;
import com.sp.ex.mapper.GoogleMapper;
import com.sp.ex.service.GoogleOAuthService;

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
				+ "scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive.metadata.readonly "
				+ "https://www.googleapis.com/auth/calendar.readonly " + "https://www.googleapis.com/auth/calendar&"
				+ "redirect_uri=http://localhost:8220/ex/Gogl/getCodePage&" + "response_type=code&"
				+ "access_type=offline&" + "client_id=" + getClientID();
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
		String param = "code=" + code
				+ "&client_id=741951758945-qtt2bbvcf6fhqm6acpim429maj11hqjp.apps.googleusercontent.com&"
				+ "client_secret=E6alvBW5f0G9YBSuBo_oTWbk&" + "redirect_uri=http://localhost:8220/ex/Gogl/getCodePage&"
				+ "grant_type=authorization_code";
		System.out.println("param = " + param);

		byte[] outputByte = param.getBytes("UTF-8");
		OutputStream os = con.getOutputStream();
		os.write(outputByte);
		os.close();
		int resCode = con.getResponseCode();
		Map<String, List<String>> header = con.getHeaderFields();

		System.out.println("header = ");
		for (Map.Entry<String, List<String>> m : header.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
		BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String line = "";
		String str = "";
		System.out.println("--------------------------------");
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
			str += line;
		}
		System.out.println("--------------------------------");

		System.out.println("rescode = " + resCode);
		TokenDTO dto = new TokenDTO();

		System.out.println("str = " + str);
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParse.parse(str);

		System.out.println("to String   =" + jsonObj.toString());
		System.out.println("access_token=" + jsonObj.get("access_token"));
		System.out.println("refresh_token=" + jsonObj.get("refresh_token"));
		System.out.println("userID=" + jsonObj.get("userID"));
		dto.setAccess_Token(jsonObj.get("access_token").toString());
		if (jsonObj.get("refresh_token") != null)
			dto.setRefresh_Token(jsonObj.get("refresh_token").toString());
		dto.setUser_ID((String) request.getSession().getAttribute("userID"));
		// System.out.println("id = "+dto.getUser_ID());
		// System.out.println("ac token = "+obj.get("access_token"));
		// System.out.println("id = "+dto.getRefresh_Token());

		googleMapper.insertToken(dto);

		return null;
	}

	@Override
	public String getAccessToken(String userID) {
		
		return googleMapper.getAccountInfo(userID).getAccess_token();
	}

	@Override
	public List<String> getServices() {
		List<String> services = null;

		return null;
	}

	@Override
	public String getAccountID(String userID) {
		System.out.println("user id = " + userID);

		return googleMapper.getAccountInfo(userID).getAccount_ID();
	}

}
