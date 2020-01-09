package com.sp.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sp.ex.dto.GoogleAccountDTO;
import com.sp.ex.dto.TokenDTO;


public interface GoogleMapper {
	public GoogleAccountDTO getAccountInfo(String userID);
	public void insertToken(TokenDTO dto);
	public void updateToken(TokenDTO dto);
	public void deleteToken(String user_ID);
	
	public List<String>getCalendarList(String user_ID);
	public void insertCalendar(String user_ID, String calendar_ID);
}
