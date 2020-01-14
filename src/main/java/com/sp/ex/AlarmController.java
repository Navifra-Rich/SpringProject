package com.sp.ex;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sp.ex.common.MainpageCommon;
import com.sp.ex.service.AlarmService;

@Controller
@RequestMapping("/Alarm")
public class AlarmController {
	
	@Autowired
	AlarmService alarmService;
	@Autowired
	MainpageCommon pageCom;
	@RequestMapping("/addAlarm")
	public void addAlarm() {
		
	}
	@RequestMapping("/getAlarmList")
	public String getAlarmList(Model model, HttpServletRequest req) {
		String user_ID = req.getSession().getAttribute("userID").toString();
		model.addAttribute("comment_alarm",alarmService.getCommentAlarmList(user_ID));
		return "/PopUp/alarm";
	}
	@RequestMapping("/clickAlarm")
	public String clickAlarm(int comment_ID,int post_ID, Model model, HttpServletRequest req) {
		alarmService.checkAlarm(Integer.toString(comment_ID));
		pageCom.selectPost(post_ID, model, req);
		return "/board/boardPost";
	}
}
