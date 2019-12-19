package com.sp.ex.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sp.ex.dto.*;
public interface BoardMapper {
	void createPost(postDTO dto);
	public void createComment(CommentDTO dto);
	public List<postDTO> viewAll();
	public postDTO showPost(int idx);
	public int getPostCount();
	
	public List<postDTO> getPostList(PagingDTO pageDTO);
	public List<CommentDTO> getCommentList(int postNum);
	public List<postDTO> getSearchedPostList(PagingDTO pageDTO);
	public List<FileDTO> getFileList(@Param("postID")String postID);
	
	public void writeFilePath(FileDTO fileDTO);
	//public void writeFilePath(@Param("boardID")String boardID, @Param("filePath")String filePath);
	public int getPostIDbyUser(String userID);
}
