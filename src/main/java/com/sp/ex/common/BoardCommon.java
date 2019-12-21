package com.sp.ex.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.sp.ex.dto.*;
import com.sp.ex.service.BoardService;


public class BoardCommon {
	
	private BoardService boardService;
	public void getBoard(Model model, int setPage) {
		PagingDTO pageDTO = new PagingDTO();
		System.out.println("page = "+setPage);
		pageDTO.setPageInfo(setPage, boardService.getPostCount(),null);
		model.addAttribute("page",pageDTO);
		model.addAttribute("viewAll",boardService.getPostList(pageDTO));
		return;
	}
}
