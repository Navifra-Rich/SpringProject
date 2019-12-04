package com.sp.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Gogl")
public class GoglController {
	
	@RequestMapping("/getToken")
	public String getToken() {
		return "";
	}
}
