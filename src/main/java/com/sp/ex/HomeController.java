package com.sp.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sp.ex.common.MainpageCommon;
import com.sp.ex.service.AlarmService;
import com.sp.ex.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	BoardService boardService;
	
	@Autowired
	AlarmService alarmService;
	
	@Autowired
	MainpageCommon mainCom;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpServletRequest req) {
		mainCom.mainPageCom(model, req);
		
		return "home";
	}

}
