package com.sp.ex;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sp.ex.dto.CommentDTO;
import com.sp.ex.dto.PagingDTO;
import com.sp.ex.dto.postDTO;
import com.sp.ex.service.AlarmService;
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

	@Autowired
	AlarmService alarmService;

	@RequestMapping("/writeComment")
	@ResponseBody
	public String writeComment(@RequestParam HashMap<Object, Object> param, Model model,
			HttpSession session
//			@RequestParam(value="id")String id,
//			@RequestParam(value="content")String content,
//			@RequestParam(value="postNum")int postNum,
//			@RequestParam("curPage")int curPage
	) {

		System.out.println("in writeComment paramSize = "+param.size());
		String id=param.get("id").toString();
		String content = param.get("content").toString();
		int postNum = Integer.parseInt(param.get("postNum").toString());
		int curPage = Integer.parseInt(param.get("curPage").toString()) ;
		CommentDTO comDTO = new CommentDTO(id, content, "123", postNum);
		commentService.writeComment(comDTO);
		postDTO selectedPost = boardService.showPost(postNum);
		model.addAttribute("selectedPost", selectedPost);
		PagingDTO pageDTO = new PagingDTO();
		pageDTO.setPageInfo(curPage, boardService.getPostCount(), null);
		model.addAttribute("page", pageDTO);
		model.addAttribute("viewAll", boardService.getPostList(pageDTO));
		model.addAttribute("comments", commentService.getCommentList(postNum));
		model.addAttribute("meeting", meetingService.getMeetingInfo(postNum));
		comment_alarm(commentService.getLastCommentNum(), selectedPost.getTitle(), selectedPost.getAuthor(), id,
				Integer.toString(postNum));
		session.setAttribute("alarmTo", selectedPost.getAuthor());
		System.out.println("알람 받을 유저 = "+selectedPost.getAuthor());
//		
		return "/board/boardPost";
	}

	public void comment_alarm(int comment_ID, String post_title, String user_ID, String writer_ID, String post_ID) {
		alarmService.addCommentAlarm(comment_ID, post_title, user_ID, writer_ID, post_ID);
	}
}
