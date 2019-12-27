package com.sp.ex.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sp.ex.dto.GoogleAccountDTO;
import com.sp.ex.dto.TokenDTO;


public interface GoogleMapper {
	public GoogleAccountDTO getAccountInfo(String userID);
	public void insertToken(TokenDTO dto);
}
