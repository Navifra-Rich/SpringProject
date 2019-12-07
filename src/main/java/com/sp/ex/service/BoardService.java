package com.sp.ex.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sp.ex.dto.*;

@Service
public interface BoardService {
		public void createPost(postDTO dto);
		public void createComment(CommentDTO dto);
		public List<postDTO> viewAll();
		public postDTO showPost(int idx);
		public int getPostCount();
		public List<postDTO> getPostList(PagingDTO pageDTO);
		public List<CommentDTO> getCommentList(int postNum);
		
}
