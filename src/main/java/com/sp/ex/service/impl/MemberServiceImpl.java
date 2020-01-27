package com.sp.ex.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sp.ex.MemberDAO;
import com.sp.ex.dto.MemberDTO;
import com.sp.ex.mapper.MemberMapper;
import com.sp.ex.service.GoogleOAuthService;
import com.sp.ex.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private GoogleOAuthService googleService;
	@Autowired
	private MemberMapper mapper;
	@Autowired
	private MemberDAO memberDAO;
	@Override
	public List<MemberDTO> viewAll() {
		return mapper.viewAll();
	}
	@Override
	public void insertMember(String id,String name, String pw) {	
		System.out.println("id = "+id+" name = "+name);

		HashMap<String, String> param = new HashMap();
		param.put("id",id);
		param.put("name",name);
		param.put("pw",pw);
		MemberDTO para = new MemberDTO(id,name,pw);
		memberDAO.memberInsert(para);
		return;
	}
	@Override
	public boolean idDuplicated(String id) {
		if(mapper.getUserInfo(id)!=null)
			return true;
		return false;
	}
	@Override
	public void signUp(String id,String name, String pw) {
		if(idDuplicated(id)) {
			return;
		} else  {
			System.out.println("회원 생성");
			memberDAO.memberInsert(new MemberDTO(id,name,pw));
		}
	}
	@Override
	public boolean logIn(String id, String pw) {
		MemberDTO dto = mapper.getUserInfo(id);
		if(dto==null||!dto.getPassword().equals(pw)) {
			System.out.println("비번틀리거나 없는 계정");
			return false;
		}else {
			System.out.println("얍");
			return true;
		}
	}
	@Override
	public void mappingUserInfo(Model model,HttpServletRequest request) {
		MemberDTO user=mapper.getUserInfo(request.getSession().getAttribute("userID").toString());
		model.addAttribute("googleAccountID",googleService.getAccountID(user.getId()));
		model.addAttribute("authorities_gogl",null);
		model.addAttribute("location", user.getLocation());
		model.addAttribute("category", user.getCategory());
	}

}

