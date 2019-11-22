package com.sp.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.ex.service.MemberService;

@Controller
@RequestMapping("/Main")
public class MainController {
	
	@Autowired
	private MemberService memberService;
	

	@RequestMapping("test")
	public String test(Model model) {
		model.addAttribute("viewAll",memberService.viewAll());
		return "member/test";
	}
	
	@RequestMapping("insertMember")
	public String insertMember(@RequestParam("id")String id, @RequestParam("name")String name, @RequestParam("pw")String pw) {
		System.out.println("Insert! id = "+id );
		memberService.insertMember(id,name,pw);
		return "member/test";
	}
	
	@RequestMapping("/home")
	public String main() {
		return "home";
	}
}
