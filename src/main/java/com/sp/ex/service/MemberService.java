package com.sp.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.ex.dto.MemberDTO;

@Service
public interface MemberService {
	
	public List<MemberDTO> viewAll();
	public void insertMember(String id,String name, String pw);
	public MemberDTO signUp(String id,String name, String pw);
	public boolean logIn(String id, String pw);
}
