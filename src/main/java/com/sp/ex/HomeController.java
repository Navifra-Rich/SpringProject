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

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpServletRequest req) {
		
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		mainCom.mainPageCom(model, req);
		/*
		 * model.addAttribute("hitContents", boardService.getHitPost());
		 * model.addAttribute("locations", boardService.getLocations());
		 * model.addAttribute("categories", boardService.getCategories());
		 * 
		 * if(req.getSession().getAttribute("userID")!=null) { String
		 * user_ID=req.getSession().getAttribute("userID").toString();
		 * model.addAttribute("alarmCount",alarmService.getCommentAlarmList(user_ID).
		 * size()); }
		 */
		
//		boardService.setBoardPage("", model, 1);
		
		return "home";
	}

}
