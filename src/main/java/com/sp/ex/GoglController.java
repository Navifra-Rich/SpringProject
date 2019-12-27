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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sp.ex.service.BoardService;
import com.sp.ex.service.GoogleCalendarService;
import com.sp.ex.service.GoogleOAuthService;

@Controller
@RequestMapping("/Gogl")
public class GoglController {

	@Autowired
	GoogleOAuthService goglOauthService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	GoogleCalendarService googleCalendarService;
	
	@RequestMapping("/getCode")
	public String getToken(HttpServletResponse response) throws Exception {
		String path=goglOauthService.getCodeURL();
		//System.out.println("code path = "+path);
		return "redirect:"+path;
	}
	@RequestMapping("/getCodePage")
	public String getCodePage(HttpServletRequest request,Model model) throws Exception {
		goglOauthService.getToken(request);
		boardService.setBoardPage("null", model, 1);
		return "/board/boardMain";
	}
	@RequestMapping("/getTokenPage")
	public String getTokenPage() {
		
		return "";
	}
	@RequestMapping("/temp")
	public String temp() throws Exception {
		googleCalendarService.temp();
		return "";
	}
	
}
