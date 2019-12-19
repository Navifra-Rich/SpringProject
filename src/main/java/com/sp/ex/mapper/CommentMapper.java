package com.sp.ex.mapper;

import java.util.List;
import com.sp.ex.dto.CommentDTO;

public interface CommentMapper {
	public void writeComment(CommentDTO comDTO);
	public List<CommentDTO> getCommentList(int postNum);
}
