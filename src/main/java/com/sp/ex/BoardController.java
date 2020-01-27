package com.sp.ex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sp.ex.common.MainpageCommon;
import com.sp.ex.dto.MeetingDTO;
import com.sp.ex.dto.PagingDTO;
import com.sp.ex.dto.postDTO;
import com.sp.ex.service.BoardService;
import com.sp.ex.service.CommentService;
import com.sp.ex.service.EventService;
import com.sp.ex.service.FileService;
import com.sp.ex.service.GoogleCalendarService;
import com.sp.ex.service.GoogleOAuthService;
import com.sp.ex.service.MeetingService;

@Controller
@RequestMapping("/Board")
public class BoardController {

	private static final String file_path = "C://Users//leesanghyeon//Desktop//abcd";
	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private EventService eventService;
	@Autowired
	private MeetingService meetingService;

	@Autowired
	private GoogleOAuthService googleService;

	@Autowired
	private GoogleCalendarService googleCalendarService;

	@Autowired
	private FileService fileService;

	@Autowired
	private MainpageCommon pageCom;
	@RequestMapping("/writeForm")
	public String wirteForm(Model model) {
		pageCom.headerNavCom(model);
		return "board/boardWrite";
	}

	// ---------------------------------------------리펙토링 해야함, 컨트롤러가 아닌 서비스쪽에서 직접적인
	// 기능 수행하도록 + 파라미터 받는 방식을 HashMap으로 간결하게+결합도 낮추기-----
	
	@RequestMapping(value="/write")
	public String writePost(HttpSession session, @RequestParam("title") String title,
			@RequestParam("content") String content, @RequestParam("time") String time,
			@RequestParam("file") MultipartFile file, @RequestParam("image") MultipartFile image,
			@RequestParam("startDay") String startDay, @RequestParam("endDay") String endDay,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
			@RequestParam("loca") String location, @RequestParam("cate") String category,
			@RequestParam("max_attendee") int max_attendee, Model model) {
		System.out.println("date = " + startDay + " startime = " + startTime);

		String userID = (String) session.getAttribute("userID");
		postDTO dto = new postDTO(0, userID, title, content, time, 0, startDay, endDay, startTime, endTime, location,
				category);
		MeetingDTO mDTO = new MeetingDTO(boardService.getLastPostNum(), userID, title, max_attendee, 0, location,
				category);
		boardService.createPost(dto);
		meetingService.createMeeting(mDTO);

		PagingDTO pageDTO =boardService.setBoardPage("", model, 1);
		model.addAttribute("page", pageDTO);
		model.addAttribute("viewAll", boardService.getPostList(pageDTO));
		pageCom.headerNavCom(model);
		
		// --------------------------파일 업로드
		String postID = boardService.getPostIDbyUser(userID);
		fileService.UplodeFileBoard(file, postID, false);
		fileService.UplodeFileBoard(image, postID, true);

		return "board/boardMain";
	}

	@RequestMapping(value = "/selectPost", method = RequestMethod.GET)
	public String selectPost(@RequestParam int idx, Model model, HttpServletRequest req) {
		System.out.println("---------------in selectPost---------------");
		System.out.println("idx = " + idx);
		pageCom.selectPost(idx,model,req);
		pageCom.headerNavCom(model);
		return "board/boardPost";
	}

	

	@RequestMapping(value = "/addComment", method = RequestMethod.GET)
	public String addComment(Model model, @RequestParam("id") String id, @RequestParam("postNum") String boardNum,
			@RequestParam("content") String content) {
		System.out.println("in addComment");
		pageCom.headerNavCom(model);
		return "board/boardPost";
	}
	@RequestMapping(value = "/getBoardList", method = RequestMethod.GET)
	public String getBoardList(Model model, @RequestParam(required = false, defaultValue = "1") int setPage,
			@RequestParam(required = false, defaultValue = "") String searchContent) {
		System.out.println("in getBoardList");
		PagingDTO pageDTO=boardService.setBoardPage(searchContent, model, setPage);
		model.addAttribute("page", pageDTO);
		model.addAttribute("viewAll", boardService.getPostList(pageDTO));
		pageCom.headerNavCom(model);
		return "board/boardMain";
	}
	@RequestMapping(value="/getPostListByLocation")
	public String getPostListByLocation(Model model, @RequestParam(required = false, defaultValue = "1") int setPage,
			@RequestParam(required = false, defaultValue = "") String searchContent,
			@RequestParam(required = false, defaultValue = "") String location) {
		System.out.println("in getBoardList by location");
		PagingDTO pageDTO=boardService.setBoardPage(searchContent, model, setPage);
		model.addAttribute("page", pageDTO);
		model.addAttribute("viewAll", boardService.getPostListByLocation(location, pageDTO));
		pageCom.headerNavCom(model);
		return "board/boardMain";
	}
	@RequestMapping(value="/getPostListByCategory")
	public String getPostListByCategory(Model model, @RequestParam(required = false, defaultValue = "1") int setPage,
			@RequestParam(required = false, defaultValue = "") String searchContent,
			@RequestParam(required = false, defaultValue = "") String category) {

		PagingDTO pageDTO = boardService.setBoardPage(searchContent, model, setPage);
		model.addAttribute("page", pageDTO);
		model.addAttribute("viewAll", boardService.getPostListByCategory(category, pageDTO));
		pageCom.headerNavCom(model);
		return "board/boardMain";
	}
}
