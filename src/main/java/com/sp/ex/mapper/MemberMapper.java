package com.sp.ex.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sp.ex.dto.MemberDTO;

public interface MemberMapper {
	public List<MemberDTO> viewAll();
	public void insertMember(MemberDTO dto);
	
	public MemberDTO getUserInfo(String id);
}
