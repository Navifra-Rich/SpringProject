package com.sp.ex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sp.ex.service.GoogleOAuthService;

@Controller
@RequestMapping("/Gogl")
public class GoglController {

	@Autowired
	GoogleOAuthService goglOauthService;
	/*
	@RequestMapping("/getCode")
	public String getToken(HttpServletResponse response) throws Exception {
		String path=goglOauthService.getCodeURL();
		//System.out.println("code path = "+path);
		return "redirect:"+path;
	}
	@RequestMapping("/getCodePage")
	public String getCodePage(HttpServletRequest request,RedirectAttributes ra) throws Exception {
		String token=goglOauthService.getToken(request);
		return "getToken";
	}
	@RequestMapping("/getTokenPage")
	public String getTokenPage() {
		
		return "";
	}
	*/
}
