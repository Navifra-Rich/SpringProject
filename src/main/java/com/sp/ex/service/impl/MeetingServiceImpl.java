package com.sp.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.ex.dto.MeetingDTO;
import com.sp.ex.mapper.MeetingMapper;
import com.sp.ex.service.MeetingService;

@Service
public class MeetingServiceImpl implements MeetingService{
	
	@Autowired
	MeetingMapper meetingMapper;
	
	@Override
	public void createMeeting(MeetingDTO dto) {
		
		meetingMapper.createMeeting(dto);
	}
	@Override
	public MeetingDTO getMeetingInfo(int post_ID) {
		return meetingMapper.getMeetingInfo(post_ID);
	}
	@Override
	public void addMember(String post_ID, String user_ID) {
		System.out.println("IN ADDMEMBER post id = "+post_ID+" user_ID = "+user_ID);
		meetingMapper.addMember(post_ID, user_ID);
	}
	@Override
	public void removeMember(String post_ID, String user_ID) {
		System.out.println("IN ADDMEMBER post id = "+post_ID+" user_ID = "+user_ID);
		meetingMapper.removeMember(post_ID, user_ID);
	}
	@Override
	public void addCurAttendeeNum(int sum, String post_ID) {
		meetingMapper.reCounnting(sum, post_ID);
	}
	@Override
	public Object isAttended(String post_ID, String user_ID) {
		if(meetingMapper.isAttended(post_ID, user_ID)!=null) return null;
		else return "";
	}
	
	@Override
	public List<MeetingDTO> getMeetingListByID(String user_ID){
		return meetingMapper.getMeetingListByID(user_ID);
	}
	
	@Override
	public List<MeetingDTO> getMeetingListByOrganizerID(String organizer_ID){
		return meetingMapper.getMeetingListByOrganizerID(organizer_ID);
	}
}
