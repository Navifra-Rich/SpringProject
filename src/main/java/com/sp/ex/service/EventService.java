package com.sp.ex.service;

import org.springframework.stereotype.Service;

@Service
public interface EventService {
	public void attendEvent(String postID, String userID);
}
