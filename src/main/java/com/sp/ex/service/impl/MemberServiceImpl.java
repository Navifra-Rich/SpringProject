package com.sp.ex.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sp.ex.MemberDAO;
import com.sp.ex.dto.MemberDTO;
import com.sp.ex.mapper.MemberMapper;
import com.sp.ex.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper mapper;
	//@Autowired
	private MemberDAO memberDAO = new MemberDAO();
	@Override
	public List<MemberDTO> viewAll() {
		// TODO Auto-generated method stub
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
		//mapper.insertMember(para);
		//memberDAO.memberInsert(para);
		return;
	}

}

