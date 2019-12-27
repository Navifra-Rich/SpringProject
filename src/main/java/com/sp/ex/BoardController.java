package com.sp.ex;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sp.ex.dto.FileDTO;
import com.sp.ex.dto.PagingDTO;
import com.sp.ex.dto.postDTO;
import com.sp.ex.service.BoardService;
import com.sp.ex.service.CommentService;
import com.sp.ex.service.EventService;
import com.sp.ex.service.GoogleOAuthService;

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
	private GoogleOAuthService googleService;
	
	@RequestMapping("/writeForm")
	public String wirteForm(HttpServletRequest request) {
		//HttpSession session = request.getSession();

		//System.out.println("user id = " + session.getAttribute("userID"));
		return "board/boardWrite";
	}
	@RequestMapping("/temp")
	public String temp(HttpServletRequest request)
	{
		
		System.out.println("tmeptmeptmpemteotmeptme");
		return "";
	}
	@RequestMapping("/write")
	public String writePost(HttpServletRequest request, @RequestParam("title") String title,
			@RequestParam("content") String content, @RequestParam("time") String time,
			@RequestParam("file") MultipartFile file, @RequestParam("date") String day,
			@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,
			Model model) {
		System.out.println("date = "+day+" startime = "+startTime);
		//----------------파일 이름 임시
		String fileName="fileName";
		
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		System.out.println("id = " + userID);

		System.out.println("in write");

		postDTO dto = new postDTO(userID, title, content, time);
		
		dto.setDay(day);
		dto.setStartTime(startTime);
		dto.setEndTime(endTime);
		
		boardService.createPost(dto);
		PagingDTO pageDTO = new PagingDTO();
		model.addAttribute("page", pageDTO);
		pageDTO.setPageInfo(1, boardService.getPostCount(), null);
		model.addAttribute("viewAll", boardService.getPostList(pageDTO));

		// --------------------------파일 업로드
		if (!file.isEmpty()) {
			int postID = boardService.getPostIDbyUser(userID);
			System.out.println("post id = " + postID);
			
			Map<String, String> map=new HashMap<String, String>();
			List<FileDTO> fileDTO = new ArrayList<FileDTO>();
			String originalFileName = file.getOriginalFilename();
			String path = file_path + "//" + originalFileName;
			try {
				map.put(Integer.toString(postID) , path);
				fileDTO.add(new FileDTO(Integer.toString(postID),path,fileName));
				
				file.transferTo(new File(path));
				boardService.writeFilePath(fileDTO);
				
			} catch (Exception e) {
				System.out.println("error");
				e.printStackTrace();
			}
		}
		return "board/boardMain";
	}

	@RequestMapping(value = "/selectPost", method = RequestMethod.GET)
	public String selectPost(@RequestParam int idx, Model model) {
		System.out.println("---------------in selectPost---------------");
		System.out.println("idx = " + idx);
		boardService.getPostInfo(idx, model);
		/*
		model.addAttribute("selectedPost", boardService.showPost(idx));
		System.out.println("selected index = " + idx);

		// ---------------------------------------------------게시글 목록
		// 불러오기-------------------------
		PagingDTO pageDTO = new PagingDTO();
		pageDTO.setPageInfo(1, boardService.getPostCount(),null);
		model.addAttribute("page", pageDTO);
		model.addAttribute("viewAll", boardService.getPostList(pageDTO));
		
		// ---------------------------------------------------첨부파일 목록
		// 불러오기-------------------------
		model.addAttribute("files",boardService.getFileList(Integer.toString(idx)));
		// ---------------------------------------------------댓글 목록
		// 불러오기-------------------------
		model.addAttribute("comments", commentService.getCommentList(idx));
*/
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
	@RequestMapping(value="/attend")
	public String attend(
			@RequestParam("postID")String postID,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userID = request.getSession().getAttribute("userID").toString();
		
		//-------------------데이터베이스에 참가자 추가--------------
		//eventService.attendEvent(postID, userID);
		//-------------------구글 캘린더 연동을 위한 토큰 얻음--------------
			//if 엑세스 토큰이 존재한다면 
			//else if 리프레쉬 토큰이 존재한다면
			//else (엑세스, 리프레쉬토큰 둘다 없는 경우)
		String codePath=googleService.getCodeURL();
		System.out.println("path = "+codePath);
		return "redirect:"+codePath;
	
	}
}
