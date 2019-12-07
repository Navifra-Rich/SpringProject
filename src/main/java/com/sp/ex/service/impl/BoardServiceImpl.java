package com.sp.ex.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.ex.dto.CommentDTO;
import com.sp.ex.dto.PagingDTO;
import com.sp.ex.dto.postDTO;
import com.sp.ex.mapper.BoardMapper;
import com.sp.ex.service.*;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	
	@Override
	public void createPost(postDTO dto) {
		System.out.println("in boardServiceImpl");
		mapper.createPost(dto);
	}


	@Override
	public List<postDTO> viewAll() {
		
		return mapper.viewAll();
	}


	@Override
	public postDTO showPost(int idx) {
		
		
		return mapper.showPost(idx);
	}


	@Override
	public int getPostCount() {
		
		return mapper.getPostCount();
	}


	@Override
	public List<postDTO> getPostList(PagingDTO pageDTO) {
		if(pageDTO.getQuery()==null) {
			return mapper.getPostList(pageDTO);	
		}else {
			System.out.println("°Ë»ö Äõ¸® = "+pageDTO.getQuery());
			return mapper.getSearchedPostList(pageDTO);
		}
		
	}


	@Override
	public void createComment(CommentDTO dto) {
		mapper.createComment(dto);
		return;
	}


	@Override
	public List<CommentDTO> getCommentList(int postNum) {
		return mapper.getCommentList(postNum);
	}

}
