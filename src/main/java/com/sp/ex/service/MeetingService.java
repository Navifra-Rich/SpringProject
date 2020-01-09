package com.sp.ex.service;

import org.springframework.stereotype.Service;

import com.sp.ex.dto.MeetingDTO;

@Service
public interface MeetingService {

	void createMeeting(MeetingDTO dto);

	MeetingDTO getMeetingInfo(int idx);

	void addMember(String post_ID, String user_ID);

	void addCurAttendeeNum(String post_ID);

	Object isAttended(String post_ID, String user_ID);

}
