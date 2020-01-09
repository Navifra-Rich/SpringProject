package com.sp.ex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sp.ex.dto.PagingDTO;
import com.sp.ex.service.BoardService;
import com.sp.ex.service.GoogleOAuthService;
import com.sp.ex.service.MemberService;

@Controller
@RequestMapping("/Main")
public class MainController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private GoogleOAuthService googleService;

	@RequestMapping(value = "/rrr")
	public String redirect(@RequestParam("code") String code) {

		return "member/test";
	}

	@RequestMapping(value = "/test")
	public String test22() throws Exception {
		String urlStr = "https://kauth.kakao.com/oauth/authorize?client_id=f7c30b063e2ae91c50963c86dae0f309&redirect_uri=http://localhost:805/ex/Main/kakaologin&response_type=code&scope=friends";

		URL url = new URL(urlStr);

		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int resCode = con.getResponseCode();
		System.out.println("response code = " + resCode);

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = "";
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}

		return "redirect:" + urlStr;
	}

	@ResponseBody
	@RequestMapping(value = "/getHomeInfo", method = RequestMethod.POST)
	public String getHomeInfo() {
		System.out.println("yeah~");
		return "yeyeyeyah";
	}

	@RequestMapping(value = "/kakaologin", method = RequestMethod.GET)
	public String rere(@RequestParam("code") String code) throws Exception {
		URL url = new URL("https://kauth.kakao.com/oauth/token");
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		con.setDoOutput(true);
		con.setDoInput(true);
		String param = "grant_type=authorization_code" + "&client_id=f7c30b063e2ae91c50963c86dae0f309"
				+ "&redirect_uri=http://localhost:805/ex/Main/kakaologin" + "&code=" + code;
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(param);
		wr.flush();
		wr.close();

		int resCode = con.getResponseCode();
		System.out.println("response code = " + resCode);
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = "";
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}

		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		return "member/test";
	}

	@RequestMapping("/getCode")
	public String test() {
		String uri = "https://kauth.kakao.com/oauth/authorize?client_id=f7c30b063e2ae91c50963c86dae0f309&redirect_uri=http://localhost:805/ex/Main/kakaologin&response_type=code";
		System.out.println("in test");
		return "redirect:" + uri;
	}

	@RequestMapping("/insertMember")
	public String insertMember(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("pw") String pw) {
		System.out.println("Insert! id = " + id);
		memberService.insertMember(id, name, pw);
		return "member/test";
	}

	@RequestMapping("/home")
	public String main() {
		return "home";
	}

	@RequestMapping("/logIn")
	public String logIn(HttpServletRequest request, @RequestParam("id") String id, @RequestParam("pw") String pw,
			Model model) {
		System.out.println("logIn in MainController");
		boolean isExist = memberService.logIn(id, pw);
		if (isExist) {
			System.out.println("logIn!");

			HttpSession session = request.getSession();
			session.setAttribute("userID", id);

			model.addAttribute("hitContents", boardService.getHitPost());
			boardService.setBoardPage("", model, 1);
		}
		return "redirect:/";

	}

	@RequestMapping("/logOut")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userID");
		return "redirect:/";
	}

	@RequestMapping("/signUp")
	public String signUp(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("pw") String pw, Model model) {
		System.out.println("sign up! in controller");

		model.addAttribute("dtos", memberService.signUp(id, name, pw));
		return "member/signUp";
	}

	@RequestMapping("/myPage")
	public String myPage(Model model, HttpServletRequest request) {
		memberService.mappingUserInfo(model, request);
		return "member/myPage";
	}

	@RequestMapping("/redirectPage")
	public String redirectPage(String request) {

		return "redirect:/" + request;
	}
}
