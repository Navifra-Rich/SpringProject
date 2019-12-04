package com.sp.ex.mapper;

import java.util.List;

import com.sp.ex.dto.*;
public interface BoardMapper {
	void createPost(postDTO dto);
	public List<postDTO> viewAll();
	public postDTO showPost(int idx);
	public int getPostCount();
	public List<postDTO> getPostList(PagingDTO pageDTO);
}
