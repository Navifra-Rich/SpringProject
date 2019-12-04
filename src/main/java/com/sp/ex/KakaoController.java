package com.sp.ex;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Kakao")
public class KakaoController {
	
	
	@RequestMapping("/refreshToken")
	public String refreshToken() {
		return "";
	}
	
	@RequestMapping("/self")
	public String self(@RequestParam("span_title")String title,@RequestParam("span_author")String author,@RequestParam("span_content")String content) throws Exception {
		String urlStr = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
		URL url = new URL(urlStr);
		HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization","Bearer fVxPsTNThOoNiKgBqODUXWjNda2DzTmfO8W9Fwo9dycAAAFuw5uz2Q");
		String orgStr="template_object=";
		String text=URLEncoder.encode("{\"object_type\":\"text\",\"text\":\"title:"+title+" author:"+author+"\",\"link\":{\"web_url\":\"https://developers.kakao.com\",\"mobile_web_url\":\"https://developers.kakao.com\"},\"button_title\":\"바로 확인\"}","UTF-8");

		//System.out.println("되냐?"+s);
	
		
		DataOutputStream oStream = new DataOutputStream(con.getOutputStream());
		oStream.writeBytes(orgStr+text);
		oStream.flush();
		oStream.close();
		System.out.println("tmp obj = "+orgStr+text);
		int responseCode = con.getResponseCode();
		System.out.println("code = "+responseCode);
		
		if(responseCode!=200) {
			BufferedReader br= new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String line="";
			while((line=br.readLine())!=null) System.out.println(line);
		}
		
		return "";
	}
	
	@RequestMapping("/getFriendList")
	public String getFreindList() throws Exception {
		String uri="https://kapi.kakao.com/v1/api/talk/friends";
		URL url = new URL(uri);
		HttpsURLConnection con= (HttpsURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		con.setRequestProperty("Authorization", "Bearer "+"Isth7zPV8o5w-JqzjQIPim-X3MSYn74Y1cJfIgorDSAAAAFuwMdE4w");
		
		System.out.println("url = "+"https://kapi.kakao.com/v1/api/talk/friends");
		System.out.println("token = "+"QsiGnheHXUJUeEBzcdc-bsM6PAvCmESf1hreTQoqAq4AAAFuwIzoUg");
		
		int responseCode = con.getResponseCode();
		
		System.out.println("res code = "+responseCode);
		
		/*
		BufferedReader er = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		String line="";
		while((line=er.readLine())!=null) {
			System.out.println(line);
		}
		*/
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line="";
		while((line=br.readLine())!=null) {
			System.out.println(line);
		}
		System.out.println("asdfasdf");
		return "board/write";
	}
	
}
