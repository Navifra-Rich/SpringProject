package com.sp.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.sp.ex.dto.*;

@Service
public interface CommentService {
	public void writeComment(CommentDTO comDTO);
	public List<CommentDTO> getCommentList(int postNum);
	int getLastCommentNum();
	void addCommentAlarm(int comment_ID, String post_title, String user_ID, String writer_ID);
}
