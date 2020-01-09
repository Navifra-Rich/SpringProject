package com.sp.ex;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sp.ex.dto.FileDTO;
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

	@RequestMapping("/writeForm")
	public String wirteForm(Model model) {
		// HttpSession session = request.getSession();
		model.addAttribute("locations", boardService.getLocations());
		model.addAttribute("categories", boardService.getCategories());
		// System.out.println("user id = " + session.getAttribute("userID"));
		return "board/boardWrite";
	}

	// ---------------------------------------------리펙토링 해야함, 컨트롤러가 아닌 서비스쪽에서 직접적인
	// 기능 수행하도록-----
	@RequestMapping("/write")
	public String writePost(HttpServletRequest request, @RequestParam("title") String title,
			@RequestParam("content") String content, @RequestParam("time") String time,
			@RequestParam("file") MultipartFile file, @RequestParam("image") MultipartFile image,
			@RequestParam("startDay") String startDay, @RequestParam("endDay") String endDay,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
			@RequestParam("loca") String location, @RequestParam("cate") String category,
			@RequestParam("max_attendee") int max_attendee, Model model) {
		System.out.println("date = " + startDay + " startime = " + startTime);

		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		System.out.println("id = " + userID);
		System.out.println("in write");

		postDTO dto = new postDTO(0, userID, title, content, time, 0, startDay, endDay, startTime, endTime, location,
				category);

		MeetingDTO mDTO = new MeetingDTO(boardService.getLastPostNum(), userID, title, max_attendee, 0, location,
				category);

		boardService.createPost(dto);
		meetingService.createMeeting(mDTO);

		PagingDTO pageDTO = new PagingDTO();
		model.addAttribute("page", pageDTO);
		pageDTO.setPageInfo(1, boardService.getPostCount(), null);
		model.addAttribute("viewAll", boardService.getPostList(pageDTO));

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
		boardService.getPostInfo(idx, model);
		model.addAttribute("meeting", meetingService.getMeetingInfo(idx));
		HttpSession session = req.getSession();
		if (session.getAttribute("userID") != null) {
			String user_ID = session.getAttribute("userID").toString();
			model.addAttribute("attended", meetingService.isAttended(Integer.toString(idx), user_ID));
		} else
			model.addAttribute("attended", null);

		return "board/boardPost";
	}

	@RequestMapping(value = "/getBoardList", method = RequestMethod.GET)
	public String getBoardList(Model model, @RequestParam(required = false, defaultValue = "1") int setPage,
			@RequestParam(required = false, defaultValue = "") String searchContent) {

		System.out.println("in getBoardList");
		boardService.setBoardPage(searchContent, model, setPage);

		return "board/boardMain";
	}

	@RequestMapping(value = "/addComment", method = RequestMethod.GET)
	public String addComment(Model model, @RequestParam("id") String id, @RequestParam("postNum") String boardNum,
			@RequestParam("content") String content) {
		System.out.println("in addComment");

		return "board/boardPost";
	}

	@RequestMapping(value = "/searchPost", method = RequestMethod.GET)
	public String searchPost(Model model, @RequestParam("content") String content) {
		System.out.println("in searchPost");
		return "board/boardMain";
	}

	/*
	 * @RequestMapping(value = "/attend") public String attend( HttpServletRequest
	 * request, HttpServletResponse response, Model model) throws Exception { String
	 * user_ID = request.getSession().getAttribute("userID").toString();
	 * 
	 * // -------------------데이터베이스에 참가자 추가-------------- //
	 * eventService.attendEvent(postID, userID); // -------------------구글 캘린더 연동을 위한
	 * 토큰 얻음-------------- // if 엑세스 토큰이 존재한다면 if
	 * (googleService.getAccessToken(user_ID) != null) {
	 * System.out.println("이미 토큰 존재함"); } // else if 리프레쉬 토큰이 존재한다면 // else (엑세스,
	 * 리프레쉬토큰 둘다 없는 경우) else { //나중에 안내 페이지를 거치도록 수정 ㄱ
	 * System.out.println("토큰 발급 필요"); String codePath = googleService.getCodeURL();
	 * System.out.println("path = " + codePath); return "redirect:" + codePath; }
	 * model.addAttribute("calendarList",googleCalendarService.getCalendarList(
	 * user_ID)); //
	 * boardService.getPostInfo(Integer.parseInt(request.getParameter("postID")), //
	 * model); return "PopUp/selectCalendar"; }
	 */
}
