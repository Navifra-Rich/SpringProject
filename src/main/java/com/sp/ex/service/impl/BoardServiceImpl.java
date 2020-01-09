package com.sp.ex.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sp.ex.dto.CommentDTO;
import com.sp.ex.dto.PagingDTO;
import com.sp.ex.dto.postDTO;
import com.sp.ex.dto.FileDTO;
import com.sp.ex.dto.IDNameDTO;
import com.sp.ex.mapper.BoardMapper;
import com.sp.ex.service.*;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public int getLastPostNum() {
		
		return mapper.getLastPostNum();
	}
	@Override
	public void createPost(postDTO dto) {
		System.out.println("post num = "+dto.getNum());
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
			System.out.println("검색 쿼리 = "+pageDTO.getQuery());
			return mapper.getSearchedPostList(pageDTO);
		}
		
	}
	@Override
	public List<postDTO> getPostListByLocation(String location, PagingDTO pageDTO){
		return mapper.getPostListByLocation(location, pageDTO.getStartPost(), pageDTO.getEndPost());
	}
	@Override
	public List<postDTO> getPostListByCategory(String category, PagingDTO pageDTO){
		return mapper.getPostListByLocation(category, pageDTO.getStartPost(), pageDTO.getEndPost());
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
	@Override
	public void writeFilePath(List<FileDTO> fileDTO) {
		for(int i=0;i<fileDTO.size();i++) {
			mapper.writeFilePath(fileDTO.get(i));
		}
		
	}
	@Override
	public List<IDNameDTO> getLocations() {
		List<IDNameDTO> dto = mapper.getLocationList();
		return dto;
	}
	@Override
	public List<IDNameDTO> getCategories() {
		List<IDNameDTO> dto = mapper.getCategoryList();
		return dto;
	}
	@Override
	public String getPostIDbyUser(String userID) {
		return mapper.getPostIDbyUser(userID);
	}
	@Override
	public List<FileDTO> getFileList(String postID) {
		System.out.println("---------------in getFileList---------------");
		return mapper.getFileList(postID);
	}
	@Override
	public void setBoardPage(String searchContent,Model model,int culPage) {
		System.out.println("query = "+searchContent);
		if (searchContent.equals("null") || searchContent.equals("")) {
			System.out.println("검색 내용 없음");

			PagingDTO pageDTO = new PagingDTO();
			pageDTO.setPageInfo(culPage, getPostCount(), null);
			model.addAttribute("page", pageDTO);
			model.addAttribute("viewAll", getPostList(pageDTO));
			List<postDTO> dto=getPostList(pageDTO);
			
			/*
			for(postDTO dtos:dto) {
				System.out.println("id "+dtos.toString());
			}
			 */
		} else {
			System.out.println("검색 내용 = " + searchContent);

			PagingDTO pageDTO = new PagingDTO();
			pageDTO.setPageInfo(culPage, getPostCount(), searchContent);
			model.addAttribute("page", pageDTO);
			model.addAttribute("viewAll", getPostList(pageDTO));
			model.addAttribute("searchQuery", searchContent);
		}
	}
	@Override
	public void getPostInfo(int idx,Model model) {
		model.addAttribute("selectedPost", showPost(idx));
		System.out.println("selected index = " + idx);

		// ---------------------------------------------------게시글 목록
		// 불러오기-------------------------
		PagingDTO pageDTO = new PagingDTO();
		pageDTO.setPageInfo(1, getPostCount(),null);
		model.addAttribute("page", pageDTO);
		model.addAttribute("viewAll", getPostList(pageDTO));
		
		// ---------------------------------------------------첨부파일 목록
		// 불러오기-------------------------
		model.addAttribute("files",getFileList(Integer.toString(idx)));
		// ---------------------------------------------------댓글 목록
		// 불러오기-------------------------
		model.addAttribute("comments", getCommentList(idx));
	}
	@Override
	public List<postDTO> getHitPost(){
		List<postDTO> dto=mapper.getHitPost();
		return dto;
	}
}
