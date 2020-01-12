package com.sp.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.ex.dto.CommentDTO;
import com.sp.ex.mapper.CommentMapper;
import com.sp.ex.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper mapper;
	@Override
	public void writeComment(CommentDTO comDTO) {
		mapper.writeComment(comDTO);
		return;
	}
	
	@Override
	public List<CommentDTO> getCommentList(int postNum) {
		
		return mapper.getCommentList(postNum);
	}

	@Override
	public void addCommentAlarm(int comment_ID, String post_title, String user_ID, String writer_ID) {
		mapper.addCommentAlarm(comment_ID, post_title, user_ID, writer_ID);
		return;
	}
	@Override
	public int getLastCommentNum() {
		return mapper.getLastCommentNum();
	}

}
