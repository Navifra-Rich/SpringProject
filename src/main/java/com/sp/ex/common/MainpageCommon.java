package com.sp.ex.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sp.ex.service.AlarmService;
import com.sp.ex.service.BoardService;
import com.sp.ex.service.MeetingService;

@Service
public class MainpageCommon {
	@Autowired
	BoardService boardService;	
	
	@Autowired
	AlarmService alarmService;
	
	@Autowired
	MeetingService meetingService;
	
	public void mainPageCom(Model model,HttpServletRequest req) {
		model.addAttribute("hitContents", boardService.getHitPost());
		model.addAttribute("locations", boardService.getLocations());
		model.addAttribute("categories", boardService.getCategories());
		
		if(req.getSession().getAttribute("userID")!=null) {
			String user_ID=req.getSession().getAttribute("userID").toString();
			model.addAttribute("alarmCount",alarmService.getCommentAlarmList(user_ID).size());
		}
		
	}
	
	public void selectPost(int post_ID, Model model,HttpServletRequest req) {
		boardService.getPostInfo(post_ID, model);
		model.addAttribute("meeting", meetingService.getMeetingInfo(post_ID));
		HttpSession session = req.getSession();
		if (session.getAttribute("userID") != null) {
			String user_ID = session.getAttribute("userID").toString();
			model.addAttribute("attended", meetingService.isAttended(Integer.toString(post_ID), user_ID));
		} else
			model.addAttribute("attended", null);

	}
}
