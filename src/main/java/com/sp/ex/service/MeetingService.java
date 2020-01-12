package com.sp.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.ex.dto.MeetingDTO;

@Service
public interface MeetingService {

	void createMeeting(MeetingDTO dto);

	MeetingDTO getMeetingInfo(int idx);

	void addMember(String post_ID, String user_ID);
	void removeMember(String post_ID, String user_ID);

	Object isAttended(String post_ID, String user_ID);

	List<MeetingDTO> getMeetingListByID(String user_ID);

	List<MeetingDTO> getMeetingListByOrganizerID(String Organier_ID);

	void addCurAttendeeNum(int sum, String post_ID);

	

}
