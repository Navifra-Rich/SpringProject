package com.sp.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.ex.dto.Comment_AlarmDTO;

@Service
public interface AlarmService {

	List<Comment_AlarmDTO> getCommentAlarmList(String user_ID);
	public void addCommentAlarm(int comment_ID, String post_title, String user_ID, String writer_ID,String post_ID);
	void checkAlarm(String comment_ID);
}
