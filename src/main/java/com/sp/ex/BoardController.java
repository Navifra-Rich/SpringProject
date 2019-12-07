package com.sp.ex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.ex.common.BoardCommon;
import com.sp.ex.dto.*;
import com.sp.ex.service.BoardService;

@Controller
@RequestMapping("/Board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	@RequestMapping("/writeForm")
	public String wirteForm() {
		return "board/boardWrite";
	}
	@RequestMapping("/write")
	public String writePost( @RequestParam("author") String author,
			@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("time") String time, Model model) {
		
		System.out.println("in write");
		postDTO dto = new postDTO( author, title, content, time);
		boardService.createPost(dto);
		PagingDTO pageDTO = new PagingDTO();
		model.addAttribute("page",pageDTO);
		pageDTO.setPageInfo(1,boardService.getPostCount(),null);
		model.addAttribute("viewAll",boardService.viewAll());
		return "board/boardMain";
	}
	@RequestMapping(value="/selectPost", method=RequestMethod.GET)
	public String selectPost(@RequestParam int idx, Model model) {
		System.out.println("in");
		System.out.println("idx = "+idx);
		model.addAttribute("selectedPost",boardService.showPost(idx));
		System.out.println("selected index = "+idx);
		
		
		//---------------------------------------------------게시글 목록 불러오기-------------------------
		PagingDTO pageDTO = new PagingDTO();
		pageDTO.setPageInfo(1, boardService.getPostCount(),null);
		model.addAttribute("page",pageDTO);
		model.addAttribute("viewAll",boardService.getPostList(pageDTO));
		//---------------------------------------------------댓글 목록 불러오기-------------------------
		//model.addAttribute("comments",boardService.getCommentList(idx));

		return "board/boardPost";
	}
	
	
	@RequestMapping(value="/getBoardList" ,method=RequestMethod.GET)
	public String getBoardList(Model model, 
			@RequestParam(required=false, defaultValue="1")int setPage,
			@RequestParam(required=false, defaultValue="")String searchContent) {
		
		
		System.out.println("in getBoardList");
		
		if(searchContent.equals("")) {
			System.out.println("검색 내용 없음");
			
			PagingDTO pageDTO = new PagingDTO();
			pageDTO.setPageInfo(setPage, boardService.getPostCount(),null);
			model.addAttribute("page",pageDTO);
			model.addAttribute("viewAll",boardService.getPostList(pageDTO));
			
		}else{
			System.out.println("검색 내용 = "+searchContent);
			
			PagingDTO pageDTO = new PagingDTO();
			pageDTO.setPageInfo(setPage, boardService.getPostCount(),searchContent);
			model.addAttribute("page",pageDTO);
			model.addAttribute("viewAll",boardService.getPostList(pageDTO));
			model.addAttribute("searchQuery",searchContent);
		}
		//---------------------------------------------------게시글 목록 불러오기-------------------------
		/*
		PagingDTO pageDTO = new PagingDTO();
		pageDTO.setPageInfo(setPage, boardService.getPostCount());
		model.addAttribute("page",pageDTO);
		model.addAttribute("viewAll",boardService.getPostList(pageDTO));
		*/
		
		return "board/boardMain";
	}
	@RequestMapping(value="/addComment", method=RequestMethod.GET)
	public String addComment(Model model,
			@RequestParam("id")String id,
			@RequestParam("postNum")String boardNum,
			@RequestParam("content")String content) {
		System.out.println("in addComment");
		CommentDTO dto=new CommentDTO(id,boardNum,content,"1");
		boardService.createComment(dto);
		
		return "board/boardPost";
	}
	
	@RequestMapping(value="/searchPost", method=RequestMethod.GET)
	public String searchPost(Model model,
			@RequestParam("content")String content) {
		
		System.out.println("in searchPost");
		
		return "board/boardMain";
	}
}
