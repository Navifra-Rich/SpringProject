package com.sp.ex.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;
@Service
public class LogServiceImpl {
	public void getResponse(int responseCode,HttpsURLConnection con) throws Exception {
		BufferedReader rd;
		if (responseCode==200) {
			rd=new BufferedReader(new InputStreamReader(con.getInputStream()));
		}else {
			rd=new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String line="";
		while((line=rd.readLine())!=null) {
			System.out.println(line);
		}
	}
}
