package com.sp.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.ex.dto.Comment_AlarmDTO;
import com.sp.ex.mapper.AlarmMapper;
import com.sp.ex.service.AlarmService;

@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	AlarmMapper alarmMapper;

	@Override
	public List<Comment_AlarmDTO> getCommentAlarmList(String user_ID) {
		return alarmMapper.getCommentAlarmList(user_ID);
	}

	@Override
	public void addCommentAlarm(int comment_ID, String post_title, String user_ID, String writer_ID, String post_ID) {
		String no = "N";
		Comment_AlarmDTO dto = new Comment_AlarmDTO(Integer.toString(comment_ID), post_ID, user_ID, writer_ID, no,
				post_title);
		alarmMapper.addCommentAlarm(dto);
	}
}
