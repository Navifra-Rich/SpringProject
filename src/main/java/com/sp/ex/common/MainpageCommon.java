package com.sp.ex.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sp.ex.dto.PagingDTO;
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
	
	public void headerNavCom(Model model){
		model.addAttribute("locations", boardService.getLocations());
		model.addAttribute("categories", boardService.getCategories());
	}
	public void mainPageCom(Model model,HttpServletRequest req) {
		model.addAttribute("hitContents", boardService.getHitPost());
		headerNavCom(model);
		model.addAttribute("recentPost",boardService.getPostList(boardService.setBoardPage("", model, 1)));
		if(req.getSession().getAttribute("userID")!=null) {
			String user_ID=req.getSession().getAttribute("userID").toString();
			alarmSet(model, user_ID);
		}
		
	}
	
	public void selectPost(int post_ID, Model model,HttpServletRequest req) {
		boardService.getPostInfo(post_ID, model);
		model.addAttribute("meeting", meetingService.getMeetingInfo(post_ID));
		HttpSession session = req.getSession();
		if (session.getAttribute("userID") != null) {
			String user_ID = session.getAttribute("userID").toString();
			alarmSet(model,user_ID);
			model.addAttribute("attended", meetingService.isAttended(Integer.toString(post_ID), user_ID));
		} else
			model.addAttribute("attended", null);
	}
	//페이지 변경 시 알람 관련 설정 세팅해줌
	public void alarmSet(Model model, String user_ID) {
		if(user_ID==null)
			return;
		model.addAttribute("alarmCount",alarmService.getCommentAlarmList(user_ID).size());
	}
}
