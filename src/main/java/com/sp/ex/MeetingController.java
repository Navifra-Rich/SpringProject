package com.sp.ex;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.ex.service.GoogleCalendarService;
import com.sp.ex.service.MeetingService;

@Controller
@RequestMapping("/Meeting")
public class MeetingController {
	
	@Autowired
	GoogleCalendarService googleCalendarService;
	
	@Autowired
	MeetingService meetingService;
	
	@RequestMapping(value = "/attendPopUp")
	public String attendPopUp(Model model, HttpServletRequest request, @RequestParam("post_ID")String post_ID) throws Exception {
		System.out.println("postNUm = "+post_ID);
		System.out.println("!!!!! = " + request.getSession().getAttribute("startTime").toString());
		String user_ID = request.getSession().getAttribute("userID").toString();
		model.addAttribute("calendarList", googleCalendarService.getCalendarList(user_ID));
		meetingService.addMember(post_ID, user_ID);
		meetingService.addCurAttendeeNum(post_ID);
		return "PopUp/attend";
	}
}
