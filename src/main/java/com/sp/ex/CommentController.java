package com.sp.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.ex.dto.CommentDTO;
import com.sp.ex.dto.PagingDTO;
import com.sp.ex.dto.postDTO;
import com.sp.ex.service.BoardService;
import com.sp.ex.service.CommentService;
import com.sp.ex.service.MeetingService;

@Controller
@RequestMapping("/Comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	MeetingService meetingService;
	
	@RequestMapping("/writeComment")
	public String writeComment(
			Model model,
			@RequestParam(value="id")String id,
			@RequestParam(value="content")String content,
			@RequestParam(value="postNum")int postNum,
			@RequestParam("curPage")int curPage
			) {
		
		System.out.println("in writeComment");
		
		CommentDTO comDTO=new CommentDTO(id,content,"123",postNum);
		commentService.writeComment(comDTO);
		postDTO selectedPost= boardService.showPost(postNum);
		model.addAttribute("selectedPost",selectedPost);
		PagingDTO pageDTO = new PagingDTO();
		pageDTO.setPageInfo(curPage, boardService.getPostCount(),null);
		model.addAttribute("page",pageDTO);
		model.addAttribute("viewAll",boardService.getPostList(pageDTO));
		model.addAttribute("comments",commentService.getCommentList(postNum));
		model.addAttribute("meeting", meetingService.getMeetingInfo(postNum));
		comment_alarm(commentService.getLastCommentNum(),selectedPost.getTitle(),selectedPost.getAuthor(),id);
		
		return "board/boardPost";
	}
	
	public void comment_alarm(int comment_ID, String post_title, String user_ID, String writer_ID) {
		commentService.addCommentAlarm(comment_ID, post_title, user_ID, writer_ID);
	}
}
