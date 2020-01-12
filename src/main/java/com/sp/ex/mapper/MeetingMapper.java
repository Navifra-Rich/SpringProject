package com.sp.ex.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sp.ex.dto.MeetingDTO;

public interface MeetingMapper {
	void createMeeting(MeetingDTO dto);
	MeetingDTO getMeetingInfo(int post_ID);
	void reCounnting(@Param("sum")int sum,@Param("post_ID") String post_ID);
	void addMember(@Param("post_ID")String post_ID,@Param("user_ID") String user_ID);
	void removeMember(@Param("post_ID")String post_ID,@Param("user_ID") String user_ID);
	
	Map<String,String> isAttended(@Param("post_ID")String post_ID, @Param("user_ID")String user_ID);
	List<MeetingDTO> getMeetingListByID(@Param("user_ID")String user_ID);
	List<MeetingDTO> getMeetingListByOrganizerID(@Param("organizer_ID")String organizer_ID);
	
}
