package com.sp.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sp.ex.dto.Comment_AlarmDTO;


public interface AlarmMapper {
	public List<Comment_AlarmDTO> getCommentAlarmList(String user_ID);
	public void addCommentAlarm(Comment_AlarmDTO dto);
	public void checkAlarm(@Param("comment_ID")String comment_ID);
}
