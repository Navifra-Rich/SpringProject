package com.sp.ex.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sp.ex.dto.GoogleCalendarDTO;
@Service
public interface GoogleCalendarService {

	void temp(HttpServletRequest request) throws Exception;

	List<GoogleCalendarDTO> getCalendarList(String user_ID) throws Exception;

	void setDefaultCalendar(String user_ID, String calendar_ID);

	String accessAPI(String path, String userID) throws Exception;

	void addEvent(String calendar_ID, String userID, HttpServletRequest request) throws Exception;

}
