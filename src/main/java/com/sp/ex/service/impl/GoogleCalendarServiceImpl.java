package com.sp.ex.service.impl;

import com.sp.ex.dto.GoogleCalendarDTO;
import com.sp.ex.mapper.GoogleMapper;
import com.sp.ex.service.GoogleCalendarService;
import com.sp.ex.service.GoogleOAuthService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleCalendarServiceImpl implements GoogleCalendarService {
	@Autowired
	private GoogleOAuthService googleOAuthService;
	@Autowired
	private GoogleMapper googleMapper;

	@Autowired
	private LogServiceImpl logService;
	private String key = "AIzaSyBUxjKZLR_-si5k7LbQhIgqpXG4k_HOlmo";

	@Override
	public void temp(HttpServletRequest request) throws Exception {
		accessAPI("https://www.googleapis.com/calendar/v3/users/me/calendarList?key=",
				request.getSession().getAttribute("userID").toString());
	}

	@Override
	public void addEvent(String calendar_ID,String userID, HttpServletRequest request) throws Exception {
		String startTime=request.getSession().getAttribute("startTime").toString();
		String endTime=request.getSession().getAttribute("endTime").toString();
		if(startTime.length()==1) {
			startTime="0"+startTime;
		}
		if(endTime.length()==1) {
			endTime="0"+endTime;
		}
		String startDay = request.getSession().getAttribute("startDay").toString()+"T"+startTime+":00:00";
		String endDay = request.getSession().getAttribute("endDay").toString()+"T"+endTime+":00:00";

		String path ="https://www.googleapis.com/calendar/v3/calendars/"+calendar_ID+"/events?key="+key;
		URL url = new URL(path);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setRequestProperty("Authorization", "Bearer "+googleOAuthService.getAccessToken(userID));
		if(googleOAuthService.getAccessToken(userID)==null) {
			System.out.println("토큰 없음");return;
		}
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json");
		System.out.println(startDay+"  and  "+endDay);
		String param=
				"{"
				+ "\"start\":{\"dateTime\":\""+startDay+"\",\"timeZone\":\"Japan\"},"
				+ "\"end\":{\"dateTime\":\""+endDay+"\",\"timeZone\":\"Japan\"}"
				+ "}";
		//2019-12-30T11:00:00
		byte[] bt=param.getBytes();
		OutputStream out=con.getOutputStream();
		out.write(bt);
		int responseCode=con.getResponseCode();
		logService.getResponse(responseCode,con);
		
	}

	@Override
	public List<GoogleCalendarDTO> getCalendarList(String user_ID) throws Exception {
		List<GoogleCalendarDTO> list = new ArrayList<GoogleCalendarDTO>();
		String res = "";
		res = accessAPI("https://www.googleapis.com/calendar/v3/users/me/calendarList?key=", user_ID);
		System.out.println(res);
		if (res.equals(""))
			return null;

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(res);
		JSONArray arr = (JSONArray) obj.get("items");

		for (int i = 0; i < arr.size(); i++) {
			JSONObject toObj = (JSONObject) arr.get(i);
			System.out.println("캘린더 이름" + toObj.get("summary"));
			GoogleCalendarDTO dto = new GoogleCalendarDTO();
			dto.setSummary((String) toObj.get("summary"));
			dto.setCalendar_ID((String) toObj.get("id"));
			list.add(dto);
		}
		if (list.size() == 0) {
			System.out.println("list is Null");
			return null;
		}
			
		return list;
	}

	@Override
	public void setDefaultCalendar(String user_ID, String calendar_ID) {
		googleMapper.insertCalendar(user_ID, calendar_ID);
		return;
	}

	// 토큰을 사용해서 api에 get방식으로 접근하는 메소드
	@Override
	public String accessAPI(String path, String userID) throws Exception {
		URL url = new URL(path + key);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("GET");
		String accessToken=googleOAuthService.getAccessToken(userID);
		System.out.println("토큰값 = "+accessToken);
		if(accessToken.equals("")) return "";
		con.setRequestProperty("Authorization", "Bearer " + accessToken);
		con.setRequestProperty("Accept", "application/json");
		int responseCode = con.getResponseCode();
		System.out.println(responseCode);
		String line = "";
		String str = "";
		if (responseCode != 200) {
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			System.out.println("error");
			while ((line = errorReader.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("엑세스 토큰 비활성화됨");

			String refreshToken = googleOAuthService.getRefreshToken(userID);
			if (refreshToken != null) {
				System.out.println("토큰 활성화 시도");
				googleOAuthService.RefreshToken(userID);

				con.disconnect();
				con = (HttpURLConnection) url.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("GET");
				con.setRequestProperty("Authorization", "Bearer " + googleOAuthService.getAccessToken(userID));
				con.setRequestProperty("Accept", "application/json");
				con.getResponseCode();
			}
			else
				return str;
		}
		System.out.println("!!여기까지");
		if (con.getInputStream() != null) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
				str += line;
			}
		}
		return str;
	}
}
