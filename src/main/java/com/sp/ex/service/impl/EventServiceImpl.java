package com.sp.ex.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.ex.mapper.EventMapper;
import com.sp.ex.service.EventService;

@Service
public class EventServiceImpl implements EventService{

	@Autowired
	EventMapper eventMapper;
	@Override
	public void attendEvent(String postID, String userID) {
		eventMapper.attendEvent(postID, userID);
		return;
	}

}
