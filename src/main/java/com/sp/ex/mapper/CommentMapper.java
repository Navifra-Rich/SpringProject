package com.sp.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sp.ex.dto.CommentDTO;

public interface CommentMapper {
	public void writeComment(CommentDTO comDTO);
	public List<CommentDTO> getCommentList(int postNum);
	public void addCommentAlarm(@Param("comment_ID")int comment_ID, @Param("post_title")String post_title,
			@Param("user_ID") String user_ID, @Param("writer_ID")String writer_ID);
	public int getLastCommentNum();
}
