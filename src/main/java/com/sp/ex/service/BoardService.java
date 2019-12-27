package com.sp.ex.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sp.ex.dto.*;

@Service
public interface BoardService {
		public void setBoardPage(String searchContent, Model model, int culPage);
	
		public void createPost(postDTO dto);
		public void createPost2(postDTO dto,Map<String, Object> map);
		public void createComment(CommentDTO dto);
		public List<postDTO> viewAll();
		public postDTO showPost(int idx);
		public int getPostCount();
		
		public List<postDTO> getPostList(PagingDTO pageDTO);
		public List<CommentDTO> getCommentList(int postNum);
		public List<FileDTO> getFileList(String postID);
		
		
		public void writeFilePath(List<FileDTO> fileDTO);
		//public void writeFilePath(Map<String, String> filePath);
		public int getPostIDbyUser(String userID);

		void getPostInfo(int idx, Model model);


	



}
