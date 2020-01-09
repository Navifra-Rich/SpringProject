package com.sp.ex.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
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
		return "E6alvBW5f0G9YBSuBo_oTWbk";
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
		googleMapper.insertToken(dto);

		return null;
	}

	@Override
	public String getAccessToken(String userID) throws Exception {
		if (googleMapper.getAccountInfo(userID) != null) {
			
			return RefreshToken(userID);
		} else
			return null;
	}

	@Override
	public String getRefreshToken(String userID) {
		String refreshToken = null;
		if (googleMapper.getAccountInfo(userID) != null)
			refreshToken = googleMapper.getAccountInfo(userID).getRefresh_token();
		return refreshToken;
	}

	@Override
	public List<String> getServices() {
		List<String> services = null;

		return null;
	}

	@Override
	public String getAccountID(String userID) {
		System.out.println("user id = " + userID);
		if (googleMapper.getAccountInfo(userID) != null)
			return googleMapper.getAccountInfo(userID).getAccount_ID();
		else {
			return "없음";
		}
	}

	@Override
	public String RefreshToken(String userID) throws Exception {
		System.out.println("----------------------토큰 활성화 진행중---------------------");
		String path = "https://www.googleapis.com/oauth2/v4/token";
		URL url = new URL(path);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		String param = "client_id=" + getClientID() + "&client_secret=" + getClientSecret() + "&refresh_token="
				+ getRefreshToken(userID) + "&grant_type=refresh_token";
		byte[] bt = param.getBytes();
		OutputStream outStream = con.getOutputStream();
		outStream.write(bt);
		outStream.close();
		int responseCode = con.getResponseCode();
		String line = "";
		String str = "";

		if (con.getErrorStream() != null) {
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			System.out.println("error");
			while ((line = errorReader.readLine()) != null) {
				System.out.println(line);
			}
			// 토큰 삭제 절차 ㄱㄱ
			System.out.println("토큰 삭제하게쓰");
			googleMapper.deleteToken(userID);
			return "";
		} else {
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((line = rd.readLine()) != null) {
				str += line;
			}
			System.out.println("str = " + str);
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(str);
			System.out.println("user id = " + userID);
			updateAccess_Token(userID, obj.get("access_token").toString());
			return  obj.get("access_token").toString();
		}
	}

	public void updateAccess_Token(String userID, String access_Token) {
		TokenDTO dto = new TokenDTO();
		dto.setUser_ID(userID);
		dto.setAccess_Token(access_Token);
		googleMapper.updateToken(dto);
		return;
	}
}
