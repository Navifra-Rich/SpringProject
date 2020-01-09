package com.sp.ex.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sp.ex.dto.*;
public interface BoardMapper {
	//---------------게시글 및 댓글 작성
	public int createPost(postDTO dto);
	public void createComment(CommentDTO dto);

	public postDTO showPost(int idx);
	public int getPostCount();
	
	public int getLastPostNum(); //사용될 post Num 구하기(동시 접근을 상정해 쓰레드 안전성 확보)
	//--------------------게시글 목록
	public List<postDTO> getPostList(PagingDTO pageDTO);
	public List<CommentDTO> getCommentList(int postNum);
	public List<postDTO> getSearchedPostList(PagingDTO pageDTO);

	public List<IDNameDTO> getLocationList();
	public List<IDNameDTO> getCategoryList();
	
	public List<postDTO> getHitPost();
	public List<postDTO> getRecentPost();
	
	//------------------------------첨부파일 및 이미지 처리
	public void writeFilePath(FileDTO fileDTO);
	public void writeImagePath(FileDTO fileDTO);
	public List<FileDTO> getFileList(@Param("postID")String postID);
	public FileDTO getImage(@Param("postID")String postID);
	
	public String getPostIDbyUser(String userID);
	
	public List<postDTO> viewAll();
}
