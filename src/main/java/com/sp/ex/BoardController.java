package com.sp.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.ex.dto.PagingDTO;
import com.sp.ex.dto.postDTO;
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
		model.addAttribute("viewAll",boardService.viewAll());
		return "board/boardMain";
	}
	@RequestMapping(value="/selectPost", method=RequestMethod.GET)
	public String selectPost(@RequestParam int idx, Model model) {
		System.out.println("in");
		System.out.println("idx = "+idx);
		model.addAttribute("selectedPost",boardService.showPost(idx));
		model.addAttribute("viewAll",boardService.viewAll());
		System.out.println("selected index = "+idx);
		return "board/boardPost";
	}
	@RequestMapping(value="/getBoardList" ,method=RequestMethod.GET)
	public String getBoardList(Model model, 
			@RequestParam(required=false, defaultValue="1")int page, 
			@RequestParam(required=false, defaultValue="1")int range) {
		
		
		System.out.println("in getBoardList");
		
		int temp = boardService.getPostCount();
		System.out.println("포스트 수 = "+temp);
		PagingDTO pageDTO = new PagingDTO();
		pageDTO.setPageInfo(page, range);
		model.addAttribute("page",pageDTO);
		model.addAttribute("viewAll",boardService.viewAll());
		return "board/boardMain";
	}
}
