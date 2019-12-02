package com.sp.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.ex.dto.postDTO;
import com.sp.ex.service.BoardService;

@Controller
@RequestMapping("/Board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("write")
	public String writePost(@RequestParam("num") int num, @RequestParam("author") String author,
			@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("time") String time, Model model) {
		postDTO dto = new postDTO(num, author, title, content, time);
		boardService.createPost(dto);
		model.addAttribute("viewAll",boardService.viewAll());
		return "board/boardWrite";
	}
	@RequestMapping("selectPost")
	public String selectPost(@RequestParam("idx") int idx,Model model) {
		model.addAttribute("viewAll",boardService.viewAll());
		model.addAttribute("selectedPost",boardService.showPost(idx));
		//System.out.println("index = "+idx);
		//postDTO dto = boardService.showPost(idx);
		//System.out.println("³»¿ë = "+boardService.showPost(idx).getContent());
		return "board/boardWrite";
	}
}
